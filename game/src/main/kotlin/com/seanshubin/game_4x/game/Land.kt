package com.seanshubin.game_4x.game

data class Land(
    val claimed:Boolean,
    val districts:Districts
) {
    constructor(resources: Resources):this(claimed = false, Districts(resources))
    fun fullyDeveloped():Boolean = districts.fullyDeveloped()
    fun claim():Land = copy(claimed = true)
    fun toObject():Map<String, Any> = mapOf(
        "claimed" to claimed,
        "districts" to districts.toObject()
    )
}
