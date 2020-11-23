package club.chachy.dbl.command.processor.utils

import club.chachy.dbl.command.Command

fun String.clean(prefixes: List<String>, suffixes: List<String>): String {
    prefixes.forEach {
        if (startsWith(it)) {
            removePrefix(it)
        }
    }
    suffixes.forEach {
        if (endsWith(it)) {
            removeSuffix(it)
        }
    }

    return this
}


fun String.alphabetOnly() = replace("[^a-zA-Z-0-9]".toRegex(), "")

fun String.numbersOnly() = replace("[^0-9]".toRegex(), "")

fun String.containsPrefixes(prefixes: List<String>) : String? {
    for (prefix in prefixes) {
        if (startsWith(prefix)) {
            return prefix
        }
    }
    return null
}


fun String.removeCommand(command: Command, prefix: String): String {
    removePrefix(prefix)
    command.aliases.let {
        it.add(command.name)
        it
    }.forEach {
        if (this == it) {
            removePrefix(it)
            return this
        }
    }

    return this
}