package club.chachy.engines.jda.entities.guild

import club.chachy.dbl.command.context.perms.utils.Permissions
import club.chachy.dbl.command.context.perms.utils.getByOffset
import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.guild.Member
import club.chachy.dbl.engine.entities.guild.Role
import club.chachy.dbl.engine.entities.user.User
import club.chachy.dbl.engine.entities.utils.OnlineStatus
import club.chachy.engines.jda.JDA
import java.time.OffsetDateTime
import net.dv8tion.jda.api.entities.Member as JDAMember

class Member(private val member: JDAMember) : Member {
    override val user: User
        get() = JDA.users?.find { it.id == member.idLong }!!
    override val guild: Guild
        get() = Guild(member.guild)
    override val timeJoined: OffsetDateTime
        get() = member.timeJoined
    override val timeBoosted: OffsetDateTime?
        get() = member.timeBoosted
    override val status: OnlineStatus
        get() = OnlineStatus[member.onlineStatus.key] ?: OnlineStatus.UNKNOWN
    override val nickname: String?
        get() = member.nickname
    override val roles: List<Role>
        get() = member.roles.map { Role(it) }
    override val permissions: List<Permissions>
        get() = member.permissions.map { getByOffset(it.offset)!! }
}