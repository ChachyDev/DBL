package club.chachy.dbl.engine.entities.guild

import club.chachy.dbl.command.context.perms.utils.Permissions
import club.chachy.dbl.engine.entities.user.User
import club.chachy.dbl.engine.entities.utils.OnlineStatus
import java.time.OffsetDateTime

interface Member {
    val user: User

    val guild: Guild

    val timeJoined: OffsetDateTime

    val timeBoosted: OffsetDateTime?

    val status: OnlineStatus

    val nickname: String?

    val roles: List<Role>

    val permissions: List<Permissions>
}