package com.seanshubin.game_4x.game

object ListUtil {
    fun <T> List<T>.updateExactlyOneWhere(predicate: (T) -> Boolean, update: (T) -> T): List<T> {
        var updateCount = 0
        val newList = map {
            if (predicate(it)) {
                updateCount++
                update(it)
            } else {
                it
            }
        }
        if(updateCount != 1){
            throw RuntimeException("Expected exactly 1 update, got $updateCount")
        }
        return newList
    }
}
