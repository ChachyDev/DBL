package club.chachy.dbl.command.processor.arguments.serialization.default.serializers

import club.chachy.dbl.command.processor.arguments.context.ArgumentContext
import club.chachy.dbl.command.processor.arguments.serialization.Serializer
import club.chachy.dbl.command.processor.utils.numbersOnly
import club.chachy.dbl.engine.entities.user.User

/**
 * Serializes raw user data to a [User] object.
 */

class UserSerializer : Serializer<User> {
    private val MENTION_REGEX = "<@!?\\d{17,20}>".toRegex()

    private val NAME_DISCRIM_REGEX = ".{2,32}#\\d{4}".toRegex()

    override fun serialize(context: ArgumentContext, raw: String): User? {
        val id = raw.toLongOrNull()

        if (id != null) {
            return context.engine.users?.find { it.id == id }
        }

        if (raw.matches(MENTION_REGEX)) {
            return context.engine.users?.find { it.id == raw.numbersOnly().toLong() }
        }

        if (raw.matches(NAME_DISCRIM_REGEX)) {
            val nameDiscrim = raw.split("#")
            return context.engine.users?.find { it.name == nameDiscrim[0] && it.discriminator == nameDiscrim[1] }
        }

        return null
    }
}