package club.chachy.dbl.engine.entities.utils

import club.chachy.dbl.engine.entities.utils.ImageFormat

interface Avatar {
    operator fun get(imageFormat: ImageFormat): String
}