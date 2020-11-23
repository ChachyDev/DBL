package club.chachy.dbl.command.event

import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.user.User

open class MessageEvent(
    open val user: User,
    open val guild: Guild?,
    open val channel: TextChannel,
    open val content: String
)

data class MessageSentEvent(
    override val user: User,
    override val guild: Guild?,
    override val channel: TextChannel,
    override val content: String
) : MessageEvent(user, guild, channel, content)

data class MessageEditedEvent(
    override val user: User,
    override val guild: Guild?,
    override val channel: TextChannel,
    override val content: String
) : MessageEvent(user, guild, channel, content)