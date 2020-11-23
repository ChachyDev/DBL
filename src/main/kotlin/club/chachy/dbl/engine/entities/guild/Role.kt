package club.chachy.dbl.engine.entities.guild

import java.awt.Color

interface Role {
    val position: Int

    val name: String

    val id : Long

    val hoisted: Boolean

    val mentionable: Boolean

    val color: Color?

    val publicRole: Boolean

    val guild: Guild
}