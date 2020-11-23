package club.chachy.dbl.command.context.utils

import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.channel.text.embeds.*
import java.awt.Color
import java.time.OffsetDateTime

class NoopEmbed : Embed {
    override val url: String?
        get() = null
    override val title: String?
        get() = null
    override val description: String?
        get() = null
    override val timestamp: OffsetDateTime?
        get() = null
    override val color: Color?
        get() = null
    override val thumbnail: ThumbnailData?
        get() = null
    override val author: AuthorData?
        get() = null
    override val footer: FooterData?
        get() = null
    override val image: ImageData?
        get() = null

    override fun sendEmbed(textChannel: TextChannel) {

    }
}