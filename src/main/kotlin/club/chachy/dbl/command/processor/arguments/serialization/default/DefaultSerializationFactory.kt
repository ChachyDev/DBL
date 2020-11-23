package club.chachy.dbl.command.processor.arguments.serialization.default

import club.chachy.dbl.command.processor.arguments.serialization.SerializationFactory
import club.chachy.dbl.command.processor.arguments.serialization.Serializer
import club.chachy.dbl.command.processor.arguments.serialization.default.serializers.*
import club.chachy.dbl.command.processor.arguments.serialization.registerSerializer

/**
 * Default serialization factory to load all serializers.
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class DefaultSerializationFactory : SerializationFactory {
    override val serializers: MutableMap<Class<*>, Serializer<*>> = mutableMapOf()

    init {
        registerSerializer(UserSerializer())
        registerSerializer(ChannelSerializer())
        registerSerializer(FloatSerializer())
        registerSerializer(DoubleSerializer())
        registerSerializer(IntSerializer())
        registerSerializer(LongSerializer())
        registerSerializer(MemberSerializer())
        registerSerializer(RoleSerializer())
        registerSerializer(StringSerializer())
        registerSerializer(GuildSerializer())
    }
}