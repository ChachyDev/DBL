package club.chachy.dbl.command.processor.arguments.serialization.default.serializers

import club.chachy.dbl.command.processor.arguments.context.ArgumentContext
import club.chachy.dbl.command.processor.arguments.serialization.Serializer

/**
 * Serializes a raw string data type to a [Long].
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class LongSerializer : Serializer<Long> {
    override fun serialize(context: ArgumentContext, raw: String) = raw.toLongOrNull()
}