package com.seanshubin.game_4x.prototype2

data class Item(val attributeMap: Map<String, Any>) {
    fun toObject():Map<String, Any> = attributeMap
}
