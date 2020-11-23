package club.chachy.engines.jda.entities.guild

import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.guild.Member
import club.chachy.dbl.engine.entities.guild.Role
import club.chachy.engines.jda.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.utils.av.Avatar
import net.dv8tion.jda.api.entities.Guild as JDAGuild

class Guild(private val guild: JDAGuild) : Guild {
    override val name: String
        get() = guild.name
    override val id: Long
        get() = guild.idLong
    override val members: List<Member>
        get() = guild.members.map { Member(it) }
    override val icon: Avatar?
        get() = guild.iconId.takeIf { it != null }?.let {
            Avatar(
                it,
                guild.id
            )
        }
    override val splash: Avatar?
        get() = guild.splashId.takeIf { it != null }?.let {
            Avatar(
                guild.id,
                it
            )
        }
    override val vanityCode: String?
        get() = guild.vanityCode
    override val description: String?
        get() = guild.description
    override val boosts: Int
        get() = guild.boostCount
    override val maxBitrate: Int
        get() = guild.maxBitrate
    override val maxFileSize: Long
        get() = guild.maxFileSize
    override val roles: List<Role>
        get() = guild.roles.map { Role(it) }
    override val channels: List<TextChannel>
        get() = guild.textChannels.map { TextChannel(it) }
}