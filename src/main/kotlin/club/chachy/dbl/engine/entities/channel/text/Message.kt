package club.chachy.dbl.engine.entities.channel.text

import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.guild.Member
import club.chachy.dbl.engine.entities.user.User
import java.time.OffsetDateTime

interface Message {
    val mentionedUsers : List<User>

    val id : Long

    val edited : Boolean

    val mentionsEveryone : Boolean

    val timeEdited : OffsetDateTime?

    val author : User

    val member : Member?

    val jumpUrl: String

    val content : String

    val channel: TextChannel

    val guild: Guild
}