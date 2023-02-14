package com.seanshubin.game_4x.game

data class XList<T>(val list: List<T>) {
    fun removeFirstEqual(target: T): XList<T> {
        val mutableList = list.toMutableList()
        val index = list.indexOf(target)
        if (index == -1) throw RuntimeException("Element '$target' not found in list of size ${list.size}")
        mutableList.removeAt(index)
        return XList(mutableList)
    }
    fun updateAtIndex(targetIndex:Int, updateFunction:(T)->T):XList<T> = XList(list.mapIndexed {  index, element ->
        if(index == targetIndex) updateFunction(element)
        else element
    })
    operator fun plus(element:T):XList<T> = XList(list + element)
    fun contains(element:T):Boolean = list.contains(element)
    fun all(predicate:(T)->Boolean):Boolean = list.all(predicate)
    fun any(predicate:(T)->Boolean):Boolean = list.any(predicate)
    fun <U> flatMap(f:(T)->List<U>):XList<U> = XList(list.flatMap(f))
    companion object {
        fun <T> emptyList() = XList(kotlin.collections.emptyList<T>())
    }
}
