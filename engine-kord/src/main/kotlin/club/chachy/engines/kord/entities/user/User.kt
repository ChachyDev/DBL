package club.chachy.engines.kord.entities.user

import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.user.User
import club.chachy.dbl.engine.entities.utils.Avatar
import com.gitlab.kordlib.rest.Image
import com.gitlab.kordlib.core.entity.User as KordUser

class User(private val user: KordUser) : User {
    override val name: String
        get() = user.username
    override val discriminator: String
        get() = user.discriminator
    override val id: Long
        get() = user.id.longValue
    override val avatar: Avatar
        get() = club.chachy.dbl.engine.entities.utils.av.AvatarLink(user.avatar.getUrl(user.avatar.takeIf { it.isAnimated }?.let { Image.Format.GIF } ?: Image.Format.PNG) ?: user.avatar.defaultUrl)
    override val mutualGuilds: List<Guild>
        get() = TODO("Not yet implemented")

    override fun toString() = user.tag
}