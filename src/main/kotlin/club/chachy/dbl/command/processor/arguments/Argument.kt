package club.chachy.dbl.command.processor.arguments

import club.chachy.dbl.command.Command
import club.chachy.dbl.command.processor.arguments.context.ArgumentContext
import club.chachy.dbl.command.processor.arguments.serialization.Serializer
import club.chachy.dbl.command.processor.utils.alphabetOnly

/**
 * Class to handle grabbing arguments from commands. Used in the CommandContext
 *
 * @param context Argument context to be provided to the serializers
 * @param serializers Serializers, used to convert raw data to a discord object. Example: <@366118780293611520> to User containing data about chachy#0001
 *
 * @author ChachyDev
 * @since 0.1-DEV
 */

class Argument(val context: ArgumentContext, val serializers: Map<Class<*>, Serializer<*>>) {
    val args = mutableMapOf<String, String>()

    /**
     * Look for the argument in the args list then look for the matching serializer for the data and attempt to serialize it
     *
     * @suppress UNCHECKED_CAST - We know the type has to be Serializer<T>
     *
     * @param name Name of the argument
     *
     * @author ChachyDev
     * @since 0.1-DEV
     */

    @Suppress("UNCHECKED_CAST")
    inline operator fun <reified T> get(name: String) : T? {
        val serializer = serializers[T::class.java] as? Serializer<T>
        return serializer?.serialize(context, args[name] ?: return null)
    }


    /**
     * Convert a raw command to a map of arguments.
     *
     * @param prefix The prefix that has been detected.
     * @param command The command that is having it's arguments processed.
     * @param raw Raw input from user to be processed.
     *
     * @author ChachyDev
     * @since 0.1-DEV
     */
    fun process(prefix: String, command: Command, raw: String) {
        val split = command.argsSpec.split(" ")
        raw.split(" ").filter { it.isNotEmpty() }.forEachIndexed { index, s ->
            if (index > 0) {
                run {
                    args[split[index - 1].alphabetOnly()] = s.removePrefix(prefix)
                }
            }
        }
    }
}