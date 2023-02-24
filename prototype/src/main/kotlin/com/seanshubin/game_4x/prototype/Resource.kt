package com.seanshubin.game_4x.prototype

data class Resource(
    val name: String,
    val prevalence: Int,
    val density: Int,
    val valuesByLocation: Map<ResourceLocation, Int>
) {
    constructor(name: String, prevalence: Int, density: Int) : this(
        name, prevalence, density, mapOf(
            ResourceLocation.UNDEVELOPED to prevalence,
            ResourceLocation.RAW to 0,
            ResourceLocation.GATHERER to 0,
            ResourceLocation.PROCESSED to 0
        )
    )

    fun valueAtLocation(location: ResourceLocation): Int = valuesByLocation.getValue(location)
    fun setValueAtLocation(location: ResourceLocation, value: Int): Resource =
        copy(valuesByLocation = valuesByLocation + (location to value))

    fun toObject(): Map<String, Any> = mapOf(
        "name" to name,
        "prevalence" to prevalence,
        "density" to density,
        "valuesByLocation" to valuesByLocation
    )

    companion object {
        val nameMatches = { resourceName: String ->
            { resource: Resource ->
                resourceName == resource.name
            }
        }
    }
}