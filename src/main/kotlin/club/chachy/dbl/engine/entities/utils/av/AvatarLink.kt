package club.chachy.dbl.engine.entities.utils.av

import club.chachy.dbl.engine.entities.utils.Avatar
import club.chachy.dbl.engine.entities.utils.ImageFormat

class AvatarLink(private val link: String) : Avatar {
    override fun get(imageFormat: ImageFormat) = link
}