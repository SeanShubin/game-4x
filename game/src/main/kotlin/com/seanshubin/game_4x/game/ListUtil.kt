package com.seanshubin.game_4x.game

object ListUtil {
    fun <T> List<T>.updateAtIndex(targetIndex:Int, updateFunction:(T)->T):List<T> = mapIndexed {  index, element ->
        if(index == targetIndex) updateFunction(element)
        else element
    }
    fun <T> List<T>.removeFirstEqual(target: T): List<T> {
        val mutableList = toMutableList()
        val index = indexOf(target)
        if (index == -1) throw RuntimeException("Element '$target' not found in list of size $size")
        mutableList.removeAt(index)
        return mutableList
    }
}
