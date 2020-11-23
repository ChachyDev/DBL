package club.chachy.dbl.engine

import club.chachy.dbl.DBL
import club.chachy.dbl.command.context.utils.WrappedEmbedBuilder
import club.chachy.dbl.command.prefix.PrefixFactory
import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.user.User

interface Engine<T, R> {
    val engineName: String

    val instance : T?

    val users : List<User>?

    val guilds: List<Guild>?

    suspend fun initialize(token: String, dbl: DBL<*, *>, prefixFactory: PrefixFactory, builder: (R.() -> Unit)?)

    fun sendEmbed(embed: WrappedEmbedBuilder, channel: TextChannel)
}