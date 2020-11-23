package club.chachy.dbl.utils

import club.chachy.dbl.engine.entities.channel.text.TextChannel

interface MessageBuilder {
    fun send(channel: TextChannel)
}