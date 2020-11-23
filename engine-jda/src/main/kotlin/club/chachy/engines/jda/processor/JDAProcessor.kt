package club.chachy.engines.jda.processor

import club.chachy.dbl.DBL
import club.chachy.dbl.command.event.MessageEditedEvent
import club.chachy.dbl.command.event.MessageSentEvent
import club.chachy.dbl.command.prefix.PrefixFactory
import club.chachy.dbl.command.processor.Processor
import club.chachy.dbl.command.processor.arguments.serialization.SerializationFactory
import club.chachy.dbl.command.processor.arguments.serialization.default.DefaultSerializationFactory
import club.chachy.dbl.command.processor.utils.containsPrefixes
import club.chachy.dbl.engine.Engine
import club.chachy.engines.jda.JDA
import club.chachy.engines.jda.entities.channel.text.TextChannel
import club.chachy.engines.jda.entities.guild.Guild
import club.chachy.engines.jda.entities.user.User
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.MessageUpdateEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class JDAProcessor(val jda: JDABuilder, override val dbl: DBL<*, *>, private val prefix: PrefixFactory, private val serialization: SerializationFactory = DefaultSerializationFactory()) : Processor, ListenerAdapter() {
    override val engine: Engine<*, *>
        get() = JDA
    override val prefixFactory: PrefixFactory
        get() = prefix
    override val serializationFactory: SerializationFactory
        get() = serialization

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val e = MessageSentEvent(User(event.author), Guild(event.guild), TextChannel(event.textChannel), event.message.contentRaw)
        val prefix = event.message.contentRaw.containsPrefixes(prefixFactory.onMessageSent(e)) ?: return
        val command = dbl.registry.modulesList.flatMap { it.commands }.find {
            val aliases = it.aliases
            aliases.add(it.name)
            val cleaned = aliases.map { a -> a.removePrefix(prefix).toLowerCase() }
            return@find cleaned.find { c -> event.message.contentRaw.removePrefix(prefix).split(" ")[0] == c } != null
        } ?: return
        process(event.message.contentRaw, prefix, e, command)
    }

    override fun onMessageUpdate(e: MessageUpdateEvent) {
        val event = MessageEditedEvent(User(e.author), Guild(e.guild), TextChannel(e.textChannel), e.message.contentRaw)
        val prefix = event.content.containsPrefixes(prefixFactory.onMessageEdited(event)) ?: return
        val command = dbl.registry.modulesList.flatMap { it.commands }.find {
            val aliases = it.aliases
            aliases.add(it.name)
            val cleaned = aliases.map { a -> a.removePrefix(prefix).toLowerCase() }
            return@find cleaned.find { c -> event.content.removePrefix(prefix).split(" ")[0] == c } != null
        } ?: return
        process(event.content, prefix, event, command)
    }

    init {
        jda.addEventListeners(this)
    }
}