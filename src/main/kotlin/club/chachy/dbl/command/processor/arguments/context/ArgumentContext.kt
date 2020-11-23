package club.chachy.dbl.command.processor.arguments.context

import club.chachy.dbl.engine.Engine
import club.chachy.dbl.engine.entities.channel.text.TextChannel
import club.chachy.dbl.engine.entities.guild.Guild
import club.chachy.dbl.engine.entities.user.User

/**
 * Context for argument processing
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */
data class ArgumentContext(val user: User, val guild: Guild?, val engine: Engine<*, *>, val channel: TextChannel?)