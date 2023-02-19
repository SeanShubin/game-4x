package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.removeAtIndex

data class Planet(
    val name:String,
    val lands: Lands = Lands.empty,
    val inOrbit: List<String> = emptyList()
) {
    fun colonize(): Planet? = removeOrbital(Names.COLONIZER)?.claimAndBuild(Names.FOOD)
    private fun removeOrbital(orbitalName: String): Planet? {
        val index = inOrbit.indexOf(orbitalName)
        if (index == -1) return null
        return copy(inOrbit = inOrbit.removeAtIndex(index))
    }

    fun setLandsWithResources(landCount:Int, resources:Resources):Planet =
        copy(lands = lands.setWithResources(landCount, resources))

    private fun claimAndBuild(resourceName: String): Planet? {
        val newLands = lands.claimAndBuild(resourceName)
        return if (newLands == null) null else copy(lands = newLands)
    }

    fun addOrbital(name: String): Planet {
        return copy(inOrbit = inOrbit + name)
    }

    fun addLand(land: Land): Planet = copy(lands = lands.addLand(land))
    fun toObject(): Map<String, Any> = mapOf(
        "name" to name,
        "lands" to lands.toObject(),
        "inOrbit" to inOrbit
    )
}
