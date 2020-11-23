package club.chachy.engines.kord.entities.channel.text

import club.chachy.dbl.engine.entities.channel.text.Message
import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.channel.utils.ChannelType
import com.gitlab.kordlib.common.entity.Snowflake
import kotlinx.coroutines.runBlocking
import com.gitlab.kordlib.core.entity.channel.MessageChannel as KordTextChannel

class TextChannel(private val channel: KordTextChannel) : TextChannel {
    override val topic: String?
        get() = channel.data.topic
    override val nsfw: Boolean
        get() = channel.data.nsfw ?: false
    override val news: Boolean
        get() = channel.data.type == com.gitlab.kordlib.common.entity.ChannelType.GuildNews
    override val slowmode: Int
        get() = channel.data.rateLimitPerUser ?: 0

    override fun deleteMessage(message: Message) {
        runBlocking {
            channel.deleteMessage(Snowflake(message.id))
        }
    }

    override fun send(string: String) {
        runBlocking {
            channel.createMessage(string)
        }
    }

    override fun toString() = channel.data.name!!

    override val name: String
        get() = channel.data.name!!
    override val id: Long
        get() = channel.id.longValue
    override val position: Int
        get() = channel.data.position ?: 0
    override val type: ChannelType
        get() = when(channel.type) {
            com.gitlab.kordlib.common.entity.ChannelType.GuildText -> ChannelType.TEXT
            com.gitlab.kordlib.common.entity.ChannelType.GuildStore -> ChannelType.VOICE
            com.gitlab.kordlib.common.entity.ChannelType.DM -> ChannelType.PRIVATE
            com.gitlab.kordlib.common.entity.ChannelType.GroupDm -> ChannelType.GROUP
            else -> ChannelType.UNKNOWN
        }
}