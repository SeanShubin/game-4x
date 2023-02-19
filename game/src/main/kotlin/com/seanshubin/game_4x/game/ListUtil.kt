package com.seanshubin.game_4x.game

object ListUtil {
    fun <T> List<T>.updateAtIndex(targetIndex: Int, updateFunction: (T) -> T): List<T> = mapIndexed { index, element ->
        if (index == targetIndex) updateFunction(element)
        else element
    }

    fun <T> List<T>.removeFirstEqual(target: T): List<T> {
        val mutableList = toMutableList()
        val index = indexOf(target)
        if (index == -1) throw RuntimeException("Element '$target' not found in list of size $size")
        mutableList.removeAt(index)
        return mutableList
    }

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

    fun <T> List<T>.updateWhere(predicate:(T)->Boolean, update:(T)->T):List<T>{
        var updateCount = 0
        val result = map {
            if(predicate(it)){
                updateCount++
                update(it)
            } else {
                it
            }
        }
        return if(updateCount == 0){
            throw RuntimeException("Unable to find element matching predicate")
        } else if (updateCount > 1){
            throw RuntimeException()
        } else {
            result
        }
    }
}
