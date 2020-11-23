package club.chachy.dbl.command.context

/**
 * Context used for when an exception has been thrown when executing a command.
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class ErrorContext {
    lateinit var exception : Throwable

    lateinit var context: CommandContext
}