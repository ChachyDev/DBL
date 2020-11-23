package club.chachy.engines.kord.entities.guild

import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.guild.Role
import kotlinx.coroutines.runBlocking
import java.awt.Color
import com.gitlab.kordlib.core.entity.Role as KordRole

class Role(val role: KordRole) : Role {
    override val position: Int
        get() = role.data.position
    override val name: String
        get() = role.name
    override val id: Long
        get() = role.id.longValue
    override val hoisted: Boolean
        get() = role.hoisted
    override val mentionable: Boolean
        get() = role.mentionable
    override val color: Color?
        get() = role.color
    override val publicRole: Boolean
        get() = false // Can't get data
    override val guild: Guild
        get() = runBlocking { Guild(role.guild.asGuild()) }
}