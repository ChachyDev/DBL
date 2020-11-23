import club.chachy.dbl.command.command
import club.chachy.dbl.command.context.perms.utils.Permissions
import club.chachy.dbl.command.event.MessageEditedEvent
import club.chachy.dbl.command.event.MessageSentEvent
import club.chachy.dbl.command.prefix.PrefixFactory
import club.chachy.dbl.dbl
import club.chachy.dbl.engine.jda.JDA
import club.chachy.dbl.engine.jda.processor.JDAProcessor
import net.dv8tion.jda.api.Permission
import java.awt.Color

fun main() {
    dbl(JDA) {
        builder {
            setToken("NTg0Mjk4MTE2ODQ1NTM1MjQx.XPI4Bg.pQoMYFM3UKqj0CNETyg6cSav6n8")
            JDAProcessor(this, this@dbl, Quick)
        }

        error {
            context.channel.send(exception.message ?: "lol")
        }

        register(overwatch())
    }.login()
}

object Quick : PrefixFactory {
    override fun onMessageSent(event: MessageSentEvent) = listOf("e!")

    override fun onMessageEdited(event: MessageEditedEvent) = listOf("e!")

}

fun overwatch() = command("test", "") {
    permissions {
        canRun = Permissions.BAN_MEMBERS in this[this@command.member ?: return@permissions]
    }
    send("Test")
}