package club.chachy.dbl.utils

fun <T> MutableList<T>.plus(element: T): MutableList<T> {
    add(element)
    return this
}