package com.seanshubin.game_4x.game

object ListUtil {
    fun <T> List<T>.removeAtIndex(targetIndex:Int):List<T> = take(targetIndex) + drop(targetIndex+1)

    fun <T> List<T>.indexOfFirstOrNull(predicate: (T) -> Boolean): Int? {
        val index = indexOfFirst(predicate)
        return if (index == -1) null else index
    }

    fun <T> List<T>.replaceAtIndex(index:Int, newElement:T):List<T> = take(index) + listOf(newElement) + drop(index+1)

    fun <T> List<T>.updateFirstApplicable(update:(T)->T?):List<T>? {
        indices.forEach { index ->
            val newValue = update(get(index))
            if(newValue != null){
                return replaceAtIndex(index, newValue)
            }
        }
        return null
    }

    fun <T> List<T>.applyToExactlyOneApplicable(predicate:(T)->Boolean, update:(T)->T?):List<T>? {
        var count = 0
        val newList = map {
            if(predicate(it)){
                val newElement = update(it)
                if(newElement == null){
                    it
                } else {
                    count++
                    newElement
                }
            } else {
                it
            }
        }
        return when (count) {
            0 -> null
            1 -> newList
            else -> throw RuntimeException("expected to only apply to one element, but applied to $count")
        }
    }
}
