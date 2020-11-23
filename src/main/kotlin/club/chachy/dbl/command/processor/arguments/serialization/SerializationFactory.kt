package club.chachy.dbl.command.processor.arguments.serialization

/**
 * Handle serialization loading.
 */

interface SerializationFactory {
    val serializers : MutableMap<Class<*>, Serializer<*>>
}

/**
 * Loads serializers
 *
 * In a separate method as inline functions cannot be inside an interface therefore resorting to using extension functions.
 *
 * @author ChachyDev
 * @since 0.-DEV
 */
inline fun <reified T> SerializationFactory.registerSerializer(serializer: Serializer<T>) {
    serializers[T::class.java] = serializer
}