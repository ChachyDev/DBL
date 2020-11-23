package club.chachy.engines.kord.entities.guild

import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.guild.Member
import club.chachy.dbl.engine.entities.guild.Role
import club.chachy.dbl.engine.entities.utils.Avatar
import club.chachy.dbl.engine.entities.utils.av.AvatarLink
import com.gitlab.kordlib.rest.Image
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import com.gitlab.kordlib.core.entity.Guild as KordGuild

class Guild(val guild: KordGuild) : Guild {
    val scope = CoroutineScope(Dispatchers.IO)

    override val name: String
        get() = guild.name
    override val id: Long
        get() = guild.id.longValue
    override val members: List<Member>
        get() = TODO("Not yet implemented")
    override val icon: Avatar?
        get() = guild.getIconUrl(Image.Format.PNG).takeIf { it != null }?.let { AvatarLink(it) }
    override val splash: Avatar?
        get() = guild.getSplashUrl(Image.Format.PNG).takeIf { it != null }?.let { AvatarLink(it) }
    override val vanityCode: String?
        get() = guild.data.vanityUrlCode
    override val description: String?
        get() = guild.description
    override val boosts: Int
        get() = 0 // Can't find a way to retrieve data.
    override val maxBitrate: Int
        get() = 0 // Can't find a way to retrieve data.
    override val maxFileSize: Long
        get() = 0 // Can't find a way to retrieve data.
    override val roles: List<Role>
        get() = runBlocking { guild.roles.toList(ArrayList()).map { Role(it) } }

    override val channels: List<TextChannel>
        get() = runBlocking { guild.channels.filterIsInstance<com.gitlab.kordlib.core.entity.channel.TextChannel>().toList(ArrayList()).map { club.chachy.engines.kord.entities.channel.text.TextChannel(it) } }
}