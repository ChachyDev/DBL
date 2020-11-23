package club.chachy.dbl.command.prefix

import club.chachy.dbl.command.event.MessageEditedEvent
import club.chachy.dbl.command.event.MessageSentEvent

class DefaultPrefixFactory(vararg val provider: String) : PrefixFactory {
    override fun onMessageSent(event: MessageSentEvent) = provider.toList()

    override fun onMessageEdited(event: MessageEditedEvent) = provider.toList()
}