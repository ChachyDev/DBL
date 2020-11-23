package club.chachy.dbl.engine.entities.user

import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.utils.Avatar

interface User {
    val name: String

    val discriminator: String

    val id: Long

    val avatar: Avatar

    val mutualGuilds: List<Guild>

    override fun toString() : String
}