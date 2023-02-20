package com.seanshubin.game_4x.game

data class Resource(
    val name: String,
    val density: Int,
    val inGround: Int,
    val gatherers: Int = 0,
    val extracted: Int = 0,
    val onSurface: Int = 0
) {
    fun endTurn():Resource = copy(extracted = 0, onSurface = 0)
    fun buildGatherer(): Resource? = if (gatherers < inGround) copy(gatherers = gatherers + 1) else null
    fun generate(): Resource? =
        if (extracted < gatherers) copy(extracted = extracted + 1, onSurface = onSurface + density)
        else null
    fun consumeFromSurface():Resource? =
        if(onSurface > 0) copy(onSurface = onSurface -1)
        else null

    fun toObject(): Map<String, Any> = mapOf(
        "name" to name,
        "density" to density,
        "inGround" to inGround,
        "gatherers" to gatherers,
        "extracted" to extracted,
        "onSurface" to onSurface
    )
}
