package club.chachy.dbl.command.processor.arguments.serialization.default.serializers

import club.chachy.dbl.command.processor.arguments.context.ArgumentContext
import club.chachy.dbl.command.processor.arguments.serialization.Serializer

/**
 * Simply allows for string data types.
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class StringSerializer : Serializer<String> {
    override fun serialize(context: ArgumentContext, raw: String) = raw
}