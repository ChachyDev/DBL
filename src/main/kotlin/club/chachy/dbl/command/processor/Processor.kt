package club.chachy.dbl.command.processor

import club.chachy.dbl.DBL
import club.chachy.dbl.command.Command
import club.chachy.dbl.command.context.CommandContext
import club.chachy.dbl.command.context.ErrorContext
import club.chachy.dbl.command.event.MessageEvent
import club.chachy.dbl.command.event.MessageSentEvent
import club.chachy.dbl.command.prefix.PrefixFactory
import club.chachy.dbl.command.processor.arguments.Argument
import club.chachy.dbl.command.processor.arguments.context.ArgumentContext
import club.chachy.dbl.command.processor.arguments.serialization.SerializationFactory
import club.chachy.dbl.command.processor.utils.removeCommand
import club.chachy.dbl.engine.Engine

interface Processor {
    val dbl: DBL<*, *>

    val engine: Engine<*, *>

    val prefixFactory: PrefixFactory

    val serializationFactory: SerializationFactory

    fun process(raw: String, prefix: String, event: MessageEvent, command: Command) {
        val argumentContext = ArgumentContext(event.user, event.guild, engine, event.channel)
        val argument = Argument(argumentContext, serializationFactory.serializers)
        argument.process(prefix, command, raw.removeCommand(command, prefix))
        val cmdContext = CommandContext(event.user, event.guild, event.channel, argument, engine, dbl)
        if (!cmdContext.permissions.canRun) return
        try {
            if (dbl.invokeBeforeCommandExecution(cmdContext) == true) return
            command.invoke?.invoke(cmdContext)
        } catch (e: Throwable) {
            val context = ErrorContext().apply {
                exception = e
                context = cmdContext
            }
            dbl.invokeError(context)
            if (dbl.settings.printStacktrace) e.printStackTrace()
        }
    }
}