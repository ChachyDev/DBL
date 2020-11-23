package club.chachy.dbl.engine.entities.utils.av

import club.chachy.dbl.engine.entities.utils.Avatar
import club.chachy.dbl.engine.entities.utils.ImageFormat

class Avatar(private val avatarId: String, private val id: String) : Avatar {
    private var link: String? = null

    override fun get(imageFormat: ImageFormat) = if (link == null) AVATAR_URL.format(avatarId, id, "." + imageFormat.format) else link!!

    companion object {
        const val AVATAR_URL = "https://cdn.discordapp.com/avatars/%s/%s.%s"
    }
}