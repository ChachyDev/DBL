package club.chachy.dbl.command.processor.arguments.serialization.default.serializers

import club.chachy.dbl.command.processor.arguments.context.ArgumentContext
import club.chachy.dbl.command.processor.arguments.serialization.Serializer
import club.chachy.dbl.engine.entities.guild.Guild

class GuildSerializer : Serializer<Guild> {
    override fun serialize(context: ArgumentContext, raw: String): Guild? {
        return raw.toLongOrNull().takeIf { it != null }?.let { context.engine.guilds?.find { guild -> guild.id == it } } ?: context.engine.guilds?.find { it.name == raw }
    }
}