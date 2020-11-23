package club.chachy.dbl.command.module

import club.chachy.dbl.command.Command
import club.chachy.dbl.command.context.CommandContext

open class Module(mName: String? = null, val description: String = "No description provided") {
    val name = mName ?: this::class.simpleName ?: "No name provided"

    val commands = mutableListOf<Command>()

    private fun command(command: Command) {
        commands.add(command)
    }

    fun command(spec: String, block: CommandContext.() -> Unit) =
        spec.let {
            val split = it.split(" ")
            command(Command(split[0], it.removePrefix(split[0] + " ")).apply {
                process {
                    block.invoke(this)
                }
            })
        }
}
