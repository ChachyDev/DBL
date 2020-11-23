package club.chachy.dbl.command

import club.chachy.dbl.command.context.CommandContext
import club.chachy.dbl.command.module.Module

class Registry {
    val modulesList : List<Module>
        get() = modules

    private val modules = mutableListOf<Module>()

    fun load(command: Module) {
        modules.add(command)
    }

    fun unload(command: Module) = modules.remove(command)
}

data class Command(val name: String, val argsSpec: String) {
    internal var invoke : (CommandContext.() -> Unit)? = null

    var aliases: MutableList<String> = mutableListOf()

    fun process(builder: CommandContext.() -> Unit) {
        invoke = builder
    }
}