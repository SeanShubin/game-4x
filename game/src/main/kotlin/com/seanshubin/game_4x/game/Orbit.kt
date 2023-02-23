package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.MapUtil.copyRemoveKey

data class Orbit(val quantityMap: Map<String, Int> = emptyMap()) {
    fun put(name: String): Orbit {
        val oldQuantity = quantityMap[name] ?: 0
        val newQuantity = oldQuantity + 1
        val newQuantityMap = quantityMap + (name to newQuantity)
        return copy(quantityMap = newQuantityMap)
    }

    fun take(name: String): Orbit {
        val oldQuantity = quantityMap[name] ?: 0
        val newQuantity = oldQuantity - 1
        if (newQuantity < 0) throw RuntimeException("Unable to take orbital $name")
        val newQuantityMap = if (newQuantity == 0) {
            quantityMap.copyRemoveKey(name)
        } else {
            quantityMap + (name to newQuantity)
        }
        return copy(quantityMap = newQuantityMap)
    }

    fun contains(name: String): Boolean = (quantityMap[name] ?: 0) > 0
    fun toObject(): Map<String, Int> = quantityMap
}