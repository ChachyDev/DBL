package club.chachy.engines.jda

import club.chachy.dbl.DBL
import club.chachy.dbl.command.context.utils.WrappedEmbedBuilder
import club.chachy.dbl.command.prefix.PrefixFactory
import club.chachy.dbl.engine.Engine
import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.channel.text.embeds.*
import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.engines.jda.entities.user.User
import club.chachy.engines.jda.processor.JDAProcessor
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import java.awt.Color
import java.time.Instant
import java.time.OffsetDateTime

object JDA : Engine<JDA, JDABuilder> {
    private var jda: JDA? = null

    override suspend fun initialize(token: String, dbl: DBL<*, *>, prefixFactory: PrefixFactory, builder: (JDABuilder.() -> Unit)?) {
        val b = JDABuilder.createDefault(token)
        builder.takeIf { it != null }?.let { b.apply(it) }
        JDAProcessor(b, dbl, prefixFactory)
        jda = b.build()
    }

    override val instance: JDA?
        get() = jda

    override val users
        get() = instance?.users?.map { User(it) }

    override fun sendEmbed(embed: WrappedEmbedBuilder, channel: TextChannel) {
        object : Embed {
            override val url: String?
                get() = embed.embedUrl
            override val title: String?
                get() = embed.embedTitle
            override val description: String?
                get() = embed.embedDescription
            override val timestamp: OffsetDateTime?
                get() = embed.embedTimestamp
            override val color: Color?
                get() = embed.embedColor
            override val thumbnail: ThumbnailData?
                get() = embed.embedThumbnailData
            override val author: AuthorData?
                get() = embed.embedAuthorData
            override val footer: FooterData?
                get() = embed.embedFooterData
            override val image: ImageData?
                get() = embed.embedImageData

            override fun sendEmbed(textChannel: TextChannel) {
                val e = EmbedBuilder()
                    .setTitle(title, url)
                    .setDescription(description)
                    .setColor(color)
                    .setThumbnail(thumbnail?.url)
                    .setAuthor(author?.name, author?.url)
                    .setFooter(footer?.text, footer?.iconUrl)
                    .setImage(image?.url)
                e.takeIf { timestamp != null }?.setTimestamp(Instant.ofEpochSecond(timestamp!!.toEpochSecond()))
                jda?.getTextChannelById(textChannel.id)?.sendMessage(e.build())?.complete()
            }

        }.sendEmbed(channel)
    }

    override val guilds: List<Guild>?
        get() = instance?.guilds?.map { club.chachy.engines.jda.entities.guild.Guild(it) }
    override val engineName: String
        get() = "JDA"
}