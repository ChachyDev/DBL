package club.chachy.dbl.command.prefix

import club.chachy.dbl.command.event.MessageEditedEvent
import club.chachy.dbl.command.event.MessageSentEvent

/**
 * Used to handle current selected prefixes gives event so that prefixes can be changed within the context of the current command
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

interface PrefixFactory {
    fun onMessageSent(event: MessageSentEvent) : List<String>

    fun onMessageEdited(event: MessageEditedEvent) : List<String>
}