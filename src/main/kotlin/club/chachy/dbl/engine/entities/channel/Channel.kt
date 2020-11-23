package club.chachy.dbl.engine.entities.channel

import club.chachy.dbl.engine.entities.channel.utils.ChannelType

interface Channel {
    val name: String

    val id : Long

    val position : Int

    val type: ChannelType
}