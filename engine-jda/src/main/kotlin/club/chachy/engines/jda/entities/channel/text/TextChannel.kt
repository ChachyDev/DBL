package club.chachy.engines.jda.entities.channel.text

import club.chachy.dbl.engine.entities.channel.text.Message
import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.channel.utils.ChannelType
import net.dv8tion.jda.api.entities.TextChannel as JDATextChannel

class TextChannel(private val textChannel: JDATextChannel) : TextChannel {
    override val topic: String?
        get() = textChannel.topic
    override val nsfw: Boolean
        get() = textChannel.isNSFW
    override val news: Boolean
        get() = textChannel.isNews
    override val slowmode: Int
        get() = textChannel.slowmode

    override fun deleteMessage(message: Message) {
        textChannel.deleteMessagesByIds(listOf(message.id.toString()))
    }

    override fun send(string: String) {
        textChannel.sendMessage(string).complete()
    }

    override fun toString() = name

    override val name: String
        get() = textChannel.name
    override val id: Long
        get() = textChannel.idLong
    override val position: Int
        get() = textChannel.position
    override val type: ChannelType
        get() = when(textChannel.type) {
            net.dv8tion.jda.api.entities.ChannelType.STORE -> ChannelType.STORE
            net.dv8tion.jda.api.entities.ChannelType.TEXT -> ChannelType.TEXT
            net.dv8tion.jda.api.entities.ChannelType.VOICE -> ChannelType.VOICE
            net.dv8tion.jda.api.entities.ChannelType.GROUP -> ChannelType.GROUP
            net.dv8tion.jda.api.entities.ChannelType.PRIVATE -> ChannelType.PRIVATE
            else -> ChannelType.UNKNOWN
        }
}