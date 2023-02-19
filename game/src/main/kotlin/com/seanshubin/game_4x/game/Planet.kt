package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.removeAtIndex
import com.seanshubin.game_4x.game.ListUtil.removeFirstEqual

data class Planet(
    val lands: Lands,
    val inOrbit: List<String>
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
    fun hasColony(): Boolean = lands.claimedExists()
    fun canColonize(): Boolean = inOrbit.contains(Names.COLONIZER) && lands.nonClaimedExists()
    private fun isColonized(): Boolean = lands.claimedExists()
    private fun hasColonizer(): Boolean = inOrbit.contains(Names.COLONIZER)
    private fun removeColonizer(): Planet = copy(inOrbit = inOrbit.removeFirstEqual(Names.COLONIZER))
    fun toObject(): Map<String, Any> = mapOf(
        "lands" to lands.toObject(),
        "inOrbit" to inOrbit
    )

    companion object {
        val empty: Planet = Planet(lands = Lands.empty, inOrbit = emptyList())
    }
}
