package club.chachy.dbl.engine.entities.channel.text

import club.chachy.dbl.engine.entities.channel.Channel
import club.chachy.dbl.engine.entities.channel.text.embeds.Embed

interface TextChannel : Channel {
    val topic : String?

    val nsfw : Boolean

    val news : Boolean

    val slowmode : Int

    fun deleteMessage(message: Message)

    fun deleteMessages(messages: Collection<Message>) = messages.forEach { deleteMessage(it) }

    fun send(string: String)

    override fun toString(): String
}