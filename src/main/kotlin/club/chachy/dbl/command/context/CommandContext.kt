package club.chachy.dbl.command.context

import club.chachy.dbl.DBL
import club.chachy.dbl.command.context.perms.Permissions
import club.chachy.dbl.command.context.utils.WrappedEmbedBuilder
import club.chachy.dbl.command.processor.arguments.Argument
import club.chachy.dbl.engine.Engine
import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.guild.Member
import club.chachy.dbl.engine.entities.user.User
import club.chachy.dbl.utils.MessageBuilder

/**
 * Context for commands.
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class CommandContext(
    val executor: User,

    val guild: Guild?,

    val channel: TextChannel,

    val args: Argument,

    val engine: Engine<*, *>,

    val dbl: DBL<*, *>
) {
    val member: Member?
        get() = guild?.members?.find { it.user.id == executor.id }

    internal var permissions = Permissions()

    fun send(message: String) = channel.send(message)

    fun send(embed: WrappedEmbedBuilder.() -> Unit) = WrappedEmbedBuilder().apply(embed).apply {
        engine.sendEmbed(this, channel)
    }

    fun send(builder: MessageBuilder) = builder.send(channel)

    fun send(message: Any) = channel.send(message.toString())

    fun permissions(block: Permissions.() -> Unit) = permissions.apply(block)
}