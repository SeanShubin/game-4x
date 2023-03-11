package com.seanshubin.game_4x.prototype2

data class SubCommand(val name: String, val calls: List<SubCommandCall>) {
    fun toObject(): Map<String, Any> = mapOf(
        "name" to name,
        "calls" to calls.map { it.toObject() }
    )
}
