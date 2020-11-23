package club.chachy.dbl.engine.entities.channel.text.embeds

import club.chachy.dbl.engine.entities.channel.text.TextChannel
import java.awt.Color
import java.time.OffsetDateTime

interface Embed {
    val url: String?

    val title: String?

    val description: String?

    val timestamp: OffsetDateTime?

    val color: Color?

    val thumbnail: ThumbnailData?

    val author: AuthorData?

    val footer: FooterData?

    val image: ImageData?
    
    fun sendEmbed(textChannel: TextChannel)
}