package club.chachy.dbl.command.processor.arguments.serialization.default.serializers

import club.chachy.dbl.command.processor.arguments.context.ArgumentContext
import club.chachy.dbl.command.processor.arguments.serialization.Serializer

/**
 * Serializes a raw string to a [Float] type.
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class FloatSerializer : Serializer<Float> {
    override fun serialize(context: ArgumentContext, raw: String) = raw.toFloatOrNull()?.takeIf { it.isFinite() }
}