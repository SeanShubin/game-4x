package com.seanshubin.game_4x.prototype

object ListUtil {
    fun <T> List<T>.removeAtIndex(targetIndex: Int): List<T> = take(targetIndex) + drop(targetIndex + 1)

    fun <T> List<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? {
        val index = indexOfFirst(predicate)
        return if (index == -1) null else index
    }

    fun <T> List<T>.replaceAtIndex(index: Int, newElement: T): List<T> =
        take(index) + listOf(newElement) + drop(index + 1)

    fun <T> List<T>.updateFirstApplicable(update: (T) -> T?): List<T>? {
        indices.forEach { index ->
            val newValue = update(get(index))
            if (newValue != null) {
                return replaceAtIndex(index, newValue)
            }
        }
        return null
    }

    fun <T> List<T>.applyToExactlyOneApplicable(predicate: (T) -> Boolean, update: (T) -> T): List<T> {
        var count = 0
        val newList = map {
            if (predicate(it)) {
                count++
                update(it)
            } else {
                it
            }
        }
        return when (count) {
            1 -> newList
            else -> throw RuntimeException("expected to apply to exactly one element, but applied to $count")
        }
    }

    fun <T> List<T>.applyAtIndex(index: Int, update: (T) -> T): List<T> =
        take(index) + update(get(index)) + drop(index + 1)
}