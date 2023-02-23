package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.applyAtIndex

data class Lands(val landList: List<Land> = emptyList()) {
    fun getSecurity(landIndex: Int): Int = landList[landIndex].security
    fun addOrbital(landIndex: Int, name: String): Lands = updateLand(landIndex) { land ->
        land.addOrbital(name)
    }

    fun setResourceAtLocation(
        landIndex: Int,
        resourceName: String,
        resourceLocation: ResourceLocation,
        newValue: Int
    ): Lands = updateLand(landIndex) { land ->
        land.setResourceAtLocation(resourceName, resourceLocation, newValue)
    }

    fun size(): Int = landList.size
    fun addLand(land: Land): Lands = copy(landList = landList + land)
    fun addLand(quantity: Int): Lands = copy(landList = landList + (1..quantity).map { Land() })
    fun setWithResources(landCount: Int, resources: Resources): Lands {
        val newLandList = (1..landCount).map { Land(resources = resources) }
        return copy(landList = newLandList)
    }

    fun addResource(landIndex: Int, resourceName: String, prevalence: Int, density: Int): Lands =
        copy(landList = landList.applyAtIndex(landIndex) { land ->
            land.addResource(resourceName, prevalence, density)
        })

    fun updateLand(landIndex: Int, update: (Land) -> Land): Lands =
        copy(landList = landList.applyAtIndex(landIndex, update))

    fun toObject(): List<Any> = landList.map { it.toObject() }
}
