package club.chachy.dbl.command.context.utils

import club.chachy.dbl.engine.entities.channel.text.embeds.AuthorData
import club.chachy.dbl.engine.entities.channel.text.embeds.FooterData
import club.chachy.dbl.engine.entities.channel.text.embeds.ImageData
import club.chachy.dbl.engine.entities.channel.text.embeds.ThumbnailData
import java.awt.Color
import java.time.OffsetDateTime

open class EmbedBuilder {
    var embedUrl: String? = null

    var embedTitle: String? = null

    var embedDescription: String? = null

    var embedTimestamp: OffsetDateTime? = null

    var embedColor: Color? = null

    var embedThumbnailData: ThumbnailData? = null

    var embedAuthorData: AuthorData? = null

    var embedFooterData: FooterData? = null

    var embedImageData: ImageData? = null
}

class WrappedEmbedBuilder : EmbedBuilder() {
    var url: String?
        get() = null
        set(value) {
            embedUrl = value
        }

    var title: String?
        get() = null
        set(value) {
            embedTitle = value
        }

    var description : String?
        get() = null
        set(value) {
            embedDescription = value
        }

    var timestamp: OffsetDateTime?
        get() = null
        set(value) {
            embedTimestamp = value
        }

    var color: Color?
        get() = null
        set(value) {
            embedColor = value
        }

    fun setThumbnail(url: String) {
        embedThumbnailData = ThumbnailData(url, 0, 0)
    }

    fun setAuthor(name: String?, url: String?, iconUrl: String?) {
        embedAuthorData = AuthorData(name, url, iconUrl)
    }

    fun setFooter(text: String?, iconUrl: String?) {
        embedFooterData = FooterData(text, iconUrl)
    }

    fun setImage(url: String) {
        embedImageData = ImageData(url, 0, 0)
    }
}