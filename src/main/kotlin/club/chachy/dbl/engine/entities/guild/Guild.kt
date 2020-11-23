package club.chachy.dbl.engine.entities.guild

import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.utils.Avatar

interface Guild {
    val name: String

    val id: Long

    val members: List<Member>

    val icon: Avatar?

    val splash: Avatar?

    val vanityCode: String?

    val description: String?

    val boosts: Int

    val maxBitrate: Int

    val maxFileSize: Long

    val roles: List<Role>

    val channels : List<TextChannel>
}