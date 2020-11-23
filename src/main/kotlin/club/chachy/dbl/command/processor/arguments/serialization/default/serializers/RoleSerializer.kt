package club.chachy.dbl.command.processor.arguments.serialization.default.serializers

import club.chachy.dbl.command.processor.arguments.context.ArgumentContext
import club.chachy.dbl.command.processor.arguments.serialization.Serializer
import club.chachy.dbl.command.processor.utils.numbersOnly
import club.chachy.dbl.engine.entities.guild.Role

/**
 * Serializes a raw role data type to a [Role] object
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class RoleSerializer : Serializer<Role> {
    private val MENTION_REGEX = "<@&?\\d{17,20}>".toRegex()

    override fun serialize(context: ArgumentContext, raw: String): Role? {
        // Guild only option
        if (context.guild == null) return null

        val id = raw.toLongOrNull()

        if (id != null) {
            return context.guild.roles.find { it.id == id }
        }

        if (raw.matches(MENTION_REGEX)) {
            return context.guild.roles.find { it.id == raw.numbersOnly().toLong() }
        }

        return context.guild.roles.find { it.name == raw }
    }
}