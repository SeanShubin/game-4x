package com.seanshubin.game_4x.game

data class Resource(
    val name: String,
    val current: Int,
    val maximum: Int,
    val rate: Int
) {
    fun buildGatherer():Resource? = if(current < maximum) copy(current = current+1) else null
    fun toObject(): Map<String, Any> = mapOf(
        "name" to name,
        "current" to current,
        "maximum" to maximum,
        "rate" to rate
    )
}
