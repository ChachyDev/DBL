package club.chachy.dbl.command.processor.arguments.serialization.default.serializers

import club.chachy.dbl.command.processor.arguments.context.ArgumentContext
import club.chachy.dbl.command.processor.arguments.serialization.Serializer

/**
 * Serializes double data types from raw strings to [Double] type
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class DoubleSerializer : Serializer<Double> {
    override fun serialize(context: ArgumentContext, raw: String) = raw.toDoubleOrNull()?.takeIf { it.isFinite() }
}