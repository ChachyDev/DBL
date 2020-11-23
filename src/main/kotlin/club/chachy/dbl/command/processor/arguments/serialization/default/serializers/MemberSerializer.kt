package club.chachy.dbl.command.processor.arguments.serialization.default.serializers

import club.chachy.dbl.command.processor.arguments.context.ArgumentContext
import club.chachy.dbl.command.processor.arguments.serialization.Serializer
import club.chachy.dbl.command.processor.utils.numbersOnly
import club.chachy.dbl.engine.entities.guild.Member

/**
 * Serializes a raw member data type to [Member] object.
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class MemberSerializer : Serializer<Member> {
    private val MENTION_REGEX = "<@!?\\d{17,20}>".toRegex()

    private val NAME_DISCRIM_REGEX = ".{2,32}#\\d{4}".toRegex()

    override fun serialize(context: ArgumentContext, raw: String): Member? {
        if (context.guild == null) return null

        val id = raw.toLongOrNull()

        if (id != null) {
            return context.guild.members.find { it.user.id == id }
        }

        if (raw.matches(MENTION_REGEX)) {
            return context.guild.members.find { it.user.id == raw.numbersOnly().toLong() }
        }

        if (raw.matches(NAME_DISCRIM_REGEX)) {
            val nameDiscrim = raw.split("#")
            return context.guild.members.find { val user = it.user; user.name == nameDiscrim[0] && user.discriminator == nameDiscrim[1] }
        }

        return null
    }
}