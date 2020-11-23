package club.chachy.engines.jda.entities.channel.text.embeds

import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.channel.text.embeds.*
import club.chachy.dbl.engine.entities.channel.text.embeds.Embed
import club.chachy.engines.jda.JDA
import net.dv8tion.jda.api.entities.MessageEmbed
import java.awt.Color
import java.time.OffsetDateTime

class Embed(private val embed: MessageEmbed) : Embed {
    override val url: String?
        get() = embed.url
    override val title: String?
        get() = embed.title
    override val description: String?
        get() = embed.description
    override val timestamp: OffsetDateTime?
        get() = embed.timestamp
    override val color: Color?
        get() = embed.color
    override val thumbnail: ThumbnailData?
        get() = embed.thumbnail.takeIf { it != null }?.let { ThumbnailData(it.url, it.width, it.height) }
    override val author: AuthorData?
        get() = embed.author.takeIf { it != null }?.let { AuthorData(it.name, it.url, it.iconUrl) }
    override val footer: FooterData?
        get() = embed.footer.takeIf { it != null }?.let { FooterData(it.text, it.iconUrl) }
    override val image: ImageData?
        get() = embed.image.takeIf { it != null }?.let { ImageData(it.url, it.width, it.height) }
    
    override fun sendEmbed(textChannel: TextChannel) {
        JDA.instance?.getTextChannelById(textChannel.id)?.sendMessage(embed)?.complete()
    }
}