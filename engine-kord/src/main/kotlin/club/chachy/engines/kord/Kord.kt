package club.chachy.engines.kord

import club.chachy.dbl.DBL
import club.chachy.dbl.command.context.utils.WrappedEmbedBuilder
import club.chachy.dbl.command.prefix.PrefixFactory
import club.chachy.dbl.engine.Engine
import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.channel.text.embeds.*
import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.user.User
import club.chachy.engines.kord.cache.UserDescription
import club.chachy.engines.kord.processor.KordProcessor
import com.gitlab.kordlib.cache.api.data.description
import com.gitlab.kordlib.common.entity.Snowflake
import com.gitlab.kordlib.core.Kord
import com.gitlab.kordlib.core.behavior.channel.createEmbed
import com.gitlab.kordlib.core.builder.kord.KordBuilder
import com.gitlab.kordlib.core.entity.channel.MessageChannel
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import java.awt.Color
import java.time.Instant
import java.time.OffsetDateTime

object Kord : Engine<Kord, KordBuilder> {
    override var instance: Kord? = null

    override val users: List<User>? get() = listOf()

    override val guilds: List<Guild>?
        get() = runBlocking {
            instance?.defaultSupplier?.guilds?.toList(ArrayList())
                ?.map { club.chachy.engines.kord.entities.guild.Guild(it) }
        }

    override suspend fun initialize(
        token: String,
        dbl: DBL<*, *>,
        prefixFactory: PrefixFactory,
        builder: (KordBuilder.() -> Unit)?
    ) {
        instance = if (builder != null) Kord(token, builder) else Kord(token)
        KordProcessor(instance!!, dbl, prefixFactory)
        val self = instance!!.getSelf()
        println("${self.tag} is online!")
        instance!!.login()
    }

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
                runBlocking {
                    val c = instance?.getChannelOf<MessageChannel>(Snowflake(textChannel.id)) ?: return@runBlocking
                    c.createEmbed {
                        url = embed.embedUrl
                        title = embed.embedTitle
                        description = embed.embedDescription
                        if (embed.embedTimestamp != null) {
                            timestamp = Instant.ofEpochSecond(embed.embedTimestamp!!.toEpochSecond())
                        }
                        color = embed.embedColor
                        if (embed.embedThumbnailData?.url != null) {
                            thumbnail {
                                url = embed.embedThumbnailData!!.url!!
                            }
                        }
                        author {
                            if (embed.embedAuthorData?.name != null) {
                                name = embed.embedAuthorData!!.name!!
                            }
                            if (embed.embedAuthorData?.iconUrl != null) {
                                icon = embed.embedAuthorData!!.iconUrl!!
                            }
                            if (embed.embedAuthorData?.url != null) {
                                url = embed.embedAuthorData!!.url!!
                            }
                        }

                        footer {
                            if (embed.embedFooterData?.text != null) {
                                text = embed.embedFooterData!!.text!!
                            }

                            if (embed.embedFooterData?.iconUrl != null) {
                                icon = embed.embedFooterData!!.iconUrl!!
                            }
                        }

                        if (embed.embedImageData?.url != null) {
                            image = embed.embedImageData!!.url!!
                        }
                    }
                }
            }

        }.sendEmbed(channel)
    }

    override val engineName: String
        get() = "Kord"
}