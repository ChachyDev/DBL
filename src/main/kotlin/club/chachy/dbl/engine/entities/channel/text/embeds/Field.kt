package club.chachy.dbl.engine.entities.channel.text.embeds

interface Field {
    val name: String

    val value: String

    val inline: Boolean
}