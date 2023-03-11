package com.seanshubin.game_4x.prototype2

data class SubCommandCall(val name: String, val parameters: List<Item>) {
    fun toObject(): Map<String, Any> = mapOf(
        "name" to name,
        "parameters" to parameters.map { it.toObject() }
    )
}
