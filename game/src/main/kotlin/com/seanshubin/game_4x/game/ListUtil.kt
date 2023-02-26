package com.seanshubin.game_4x.game

object ListUtil {
    fun <T> List<T>.updateWhere(predicate: (T) -> Boolean, update: (T) -> T): List<T> =
        map {
            if (predicate(it)) {
                update(it)
            } else {
                it
            }
        }

    fun <T> List<T>.updateAtIndex(index: Int, update: (T) -> T): List<T> =
        take(index) + update(get(index)) + drop(index + 1)
}
