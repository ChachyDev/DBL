package club.chachy.dbl

import club.chachy.dbl.command.Registry
import club.chachy.dbl.command.context.CommandContext
import club.chachy.dbl.command.context.ErrorContext
import club.chachy.dbl.command.module.Module
import club.chachy.dbl.command.prefix.DefaultPrefixFactory
import club.chachy.dbl.command.prefix.PrefixFactory
import club.chachy.dbl.engine.Engine
import club.chachy.dbl.utils.config.Config
import club.chachy.dbl.utils.config.TOKEN_PLACEHOLDER

class DBL<T, R> {
    val registry = Registry()

    internal val settings = Settings()

    internal var engine: Engine<T, R>? = null

    private var builder: (R.() -> Unit)? = null

    private var error: (ErrorContext.() -> Unit)? = null

    private var beforeCommandExecution: (CommandContext.() -> Boolean)? = null

    suspend fun login(token: String) {
        engine?.initialize(token.takeIf { it == TOKEN_PLACEHOLDER }
            ?.let { settings.config?.token ?: error("Token is null") } ?: token, this, settings.prefixFactory, builder)
            ?: error("Engine has not been chosen, this is impossible!")
    }

    fun builder(block: R.() -> Unit) {
        builder = block
    }

    fun error(block: ErrorContext.() -> Unit) {
        error = block
    }

    fun beforeCommandExecution(block: CommandContext.() -> Boolean) {
        beforeCommandExecution = block
    }

    fun settings(block: Settings.() -> Unit) = settings.apply(block)

    internal fun invokeError(context: ErrorContext) = error?.invoke(context)

    internal fun invokeBeforeCommandExecution(context: CommandContext) = beforeCommandExecution?.invoke(context)

    fun module(name: String, description: String = "No description provided", block: Module.() -> Unit) =
        registry.load(Module(name, description).apply(block))
}

class Settings {
    var printStacktrace = false

    var prefixFactory: PrefixFactory = DefaultPrefixFactory("!")

    var config: Config? = null
}

fun <T, R> dbl(engine: Engine<T, R>, dbl: DBL<T, R>.() -> Unit) = DBL<T, R>().apply(dbl).apply {
    this.engine = engine
}