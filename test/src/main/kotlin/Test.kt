import club.chachy.dbl.DBL
import club.chachy.dbl.command.module.Module
import club.chachy.dbl.dbl
import club.chachy.dbl.utils.config.Config
import club.chachy.dbl.utils.config.TOKEN_PLACEHOLDER
import club.chachy.engines.kord.Kord

data class Config(override val token: String) : Config

suspend fun main() {
    dbl(Kord) {
        settings {
            config = Config("")
            printStacktrace = true
        }

        core()
    }.login(TOKEN_PLACEHOLDER)
}

fun DBL<*, *>.core() = module("Core") {
    help()
}


fun Module.help() = command("help") {
    send("test")
}