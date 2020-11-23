package club.chachy.engines.jda.entities.user

import club.chachy.dbl.engine.entities.utils.Avatar
import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.user.User
import club.chachy.dbl.engine.entities.utils.av.AvatarLink
import net.dv8tion.jda.api.entities.User as JDAUser

class User(private val user: JDAUser) : User {
    override val name: String
        get() = user.name
    override val discriminator: String
        get() = user.discriminator
    override val id: Long
        get() = user.idLong
    override val avatar: Avatar
        get() = AvatarLink(user.effectiveAvatarUrl)
    override val mutualGuilds: List<Guild>
        get() = user.mutualGuilds.map { club.chachy.engines.jda.entities.guild.Guild(it) }

    override fun toString() = "$name#$discriminator"
}