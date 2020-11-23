package club.chachy.engines.jda.entities.guild

import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.guild.Role
import net.dv8tion.jda.api.entities.Role as JDARole
import java.awt.Color

class Role(private val role: JDARole) : Role {
    override val position: Int
        get() = role.position
    override val name: String
        get() = role.name
    override val id: Long
        get() = role.idLong
    override val hoisted: Boolean
        get() = role.isHoisted
    override val mentionable: Boolean
        get() = role.isMentionable
    override val color: Color?
        get() = role.color
    override val publicRole: Boolean
        get() = role.isPublicRole
    override val guild: Guild
        get() = Guild(role.guild)
}