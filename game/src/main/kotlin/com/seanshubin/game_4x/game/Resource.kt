package com.seanshubin.game_4x.game

data class Resource(
    val name:String,
    val quantity:Int,
    val rate:Int) {
    fun toObject():Map<String, Any> = mapOf(
        "name" to name,
        "quantity" to quantity,
        "rate" to rate
    )
}
