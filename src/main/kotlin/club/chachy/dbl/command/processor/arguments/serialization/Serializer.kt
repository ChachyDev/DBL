package club.chachy.dbl.command.processor.arguments.serialization

import club.chachy.dbl.command.processor.arguments.context.ArgumentContext

/**
 * Superclass for serializers
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

interface Serializer<T> {
    /**
     * Returns a nullable version of [T] as the data may be unserializable.
     */
    fun serialize(context: ArgumentContext, raw: String) : T?
}