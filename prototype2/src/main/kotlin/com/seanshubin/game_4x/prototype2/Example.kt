package com.seanshubin.game_4x.prototype2

data class Example(
    val parameters: List<Pair<String, Any>>,
    val stateBefore: Items,
    val log: List<String>,
    val stateAfter: Items
) {
    fun toObject():Map<String, Any> = mapOf(
        "parameters" to parameters,
        "stateBefore" to stateBefore.toObject(),
        "log" to log,
        "stateAfter" to stateAfter.toObject()
    )
}
