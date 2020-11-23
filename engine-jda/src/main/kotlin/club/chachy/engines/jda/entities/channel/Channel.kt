package club.chachy.engines.jda.entities.channel

import club.chachy.dbl.engine.entities.channel.Channel
import club.chachy.dbl.engine.entities.channel.utils.ChannelType
import net.dv8tion.jda.api.entities.GuildChannel

class Channel(private val channel: GuildChannel) : Channel {
    override val name: String
        get() = channel.name
    override val id: Long
        get() = channel.idLong
    override val position: Int
        get() = channel.position
    override val type: ChannelType
        get() = when(channel.type) {
            net.dv8tion.jda.api.entities.ChannelType.STORE -> ChannelType.STORE
            net.dv8tion.jda.api.entities.ChannelType.TEXT -> ChannelType.TEXT
            net.dv8tion.jda.api.entities.ChannelType.VOICE -> ChannelType.VOICE
            net.dv8tion.jda.api.entities.ChannelType.GROUP -> ChannelType.GROUP
            net.dv8tion.jda.api.entities.ChannelType.PRIVATE -> ChannelType.PRIVATE
            else -> ChannelType.UNKNOWN
        }
}