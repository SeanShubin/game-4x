package com.seanshubin.game_4x.game

data class Planet(
    val name: String,
    val lands: Lands = Lands()
) {
    fun getSecurity(landIndex: Int): Int = lands.getSecurity(landIndex)
    fun addOrbital(landIndex: Int, orbitalName: String): Planet = copy(lands = lands.addOrbital(landIndex, orbitalName))
    fun setResourceAtLocation(
        landIndex: Int,
        resourceName: String,
        resourceLocation: ResourceLocation,
        newValue: Int
    ): Planet =
        copy(lands = lands.setResourceAtLocation(landIndex, resourceName, resourceLocation, newValue))

    fun addResource(landIndex: Int, resourceName: String, prevalence: Int, density: Int): Planet =
        copy(lands = lands.addResource(landIndex, resourceName, prevalence, density))

    fun landCount(): Int = lands.landList.size
    fun land(index: Int): Land = lands.landList[index]
    fun updateLand(landIndex: Int, update: (Land) -> Land): Planet =
        copy(lands = lands.updateLand(landIndex, update))

    fun setLandsWithResources(landCount: Int, resources: Resources): Planet =
        copy(lands = lands.setWithResources(landCount, resources))

    fun addLand(land: Land): Planet = copy(lands = lands.addLand(land))
    fun addLand(quantity: Int): Planet = copy(lands = lands.addLand(quantity))
    fun toObject(): Map<String, Any> = mapOf(
        "name" to name,
        "lands" to lands.toObject()
    )

    companion object {
        val nameMatches = { planetName: String ->
            { planet: Planet ->
                planetName == planet.name
            }
        }
    }
}
