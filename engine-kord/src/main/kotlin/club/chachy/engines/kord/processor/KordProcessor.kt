package club.chachy.engines.kord.processor

import club.chachy.dbl.DBL
import club.chachy.dbl.command.event.MessageSentEvent
import club.chachy.dbl.command.prefix.PrefixFactory
import club.chachy.dbl.command.processor.Processor
import club.chachy.dbl.command.processor.arguments.serialization.SerializationFactory
import club.chachy.dbl.command.processor.arguments.serialization.default.DefaultSerializationFactory
import club.chachy.dbl.command.processor.utils.containsPrefixes
import club.chachy.dbl.engine.Engine
import club.chachy.engines.kord.Kord
import club.chachy.engines.kord.entities.channel.text.TextChannel
import club.chachy.engines.kord.entities.guild.Guild
import club.chachy.engines.kord.entities.user.User
import com.gitlab.kordlib.core.event.message.MessageCreateEvent
import com.gitlab.kordlib.core.event.message.MessageUpdateEvent
import com.gitlab.kordlib.core.on

class KordProcessor(kord: com.gitlab.kordlib.core.Kord, override val dbl: DBL<*, *>, private val prefix: PrefixFactory, private val serialization: SerializationFactory = DefaultSerializationFactory()) : Processor {
    override val engine: Engine<*, *>
        get() = Kord
    override val prefixFactory: PrefixFactory
        get() = prefix
    override val serializationFactory: SerializationFactory
        get() = serialization

    init {
        kord.on<MessageCreateEvent> {
            val content = message.content
            val event = MessageSentEvent(User(message.author ?: return@on), getGuild().takeIf { it != null }?.let { Guild(it) }, TextChannel(message.channel.asChannel()), content)
            val prefix= content.containsPrefixes(prefixFactory.onMessageSent(event)) ?: return@on
            val nonPrefixed = content.removePrefix(prefix)
            val command = dbl.registry.modulesList.flatMap { it.commands }.find {
                val split = nonPrefixed.split(" ")
                it.aliases.plus(it.name).map { name -> name.toLowerCase() }.contains(split[0])
            } ?: return@on
            process(content, prefix, event, command)
        }

        kord.on<MessageUpdateEvent> {
            val message = message.asMessage()
            val content = message.content
            val event = MessageSentEvent(User(message.author ?: return@on), message.getGuildOrNull().takeIf { it != null }?.let { Guild(it) }, TextChannel(message.channel.asChannel()), content)
            val prefix= content.containsPrefixes(prefixFactory.onMessageSent(event)) ?: return@on
            val nonPrefixed = content.removePrefix(prefix)
            val command = dbl.registry.modulesList.flatMap { it.commands }.find {
                val split = nonPrefixed.split(" ")
                it.aliases.plus(it.name).map { name -> name.toLowerCase() }.contains(split[0])
            } ?: return@on
            process(content, prefix, event, command)
        }
    }
}