package club.chachy.engines.jda.entities.channel.text

import club.chachy.dbl.engine.entities.channel.text.Message
import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.user.User
import club.chachy.engines.jda.entities.guild.Member
import java.time.OffsetDateTime
import net.dv8tion.jda.api.entities.Message as JDAMessage

class Message(private val message: JDAMessage) : Message {
    override val mentionedUsers: List<User>
        get() = message.mentionedUsers.map { club.chachy.engines.jda.entities.user.User(it) }
    override val id: Long
        get() = message.idLong
    override val edited: Boolean
        get() = message.isEdited
    override val mentionsEveryone: Boolean
        get() = message.mentionsEveryone()
    override val timeEdited: OffsetDateTime?
        get() = message.timeEdited
    override val author: User
        get() = club.chachy.engines.jda.entities.user.User(message.author)
    override val member: Member?
        get() = message.member.takeIf { it != null }?.let { Member(it) }
    override val jumpUrl: String
        get() = message.jumpUrl
    override val content: String
        get() = message.contentRaw
    override val channel: TextChannel
        get() = TextChannel(message.textChannel)
    override val guild: Guild
        get() = club.chachy.engines.jda.entities.guild.Guild(message.guild)
}