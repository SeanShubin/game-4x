package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateAtIndex
import com.seanshubin.game_4x.game.ListUtil.updateWhere

data class Universe(val planets: List<Planet> = emptyList()) : HasToObject {
    val planetsByName = planets.associateBy { it.name }
    fun addPlanet(name: String): Universe = copy(planets = planets + Planet(name))
    fun updatePlanet(planetName: String, update: (Planet) -> Planet): Universe =
        copy(planets = planets.updateWhere({ it.name == planetName }, update))

    fun updateLand(planetName: String, landIndex: Int, update: (Land) -> Land): Universe =
        updatePlanet(planetName) { planet ->
            planet.copy(lands = planet.lands.updateAtIndex(landIndex, update))
        }

    fun updateEachPlanet(update: (Planet) -> Planet): Universe =
        copy(planets = planets.map(update))

    fun updateEachLand(updateLand: (Land) -> Land): Universe =
        updateEachPlanet { it.updateEachLand(updateLand) }

    override fun toObject(): Map<String, Any> = mapOf(
        "planets" to planets.map { it.toObject() }
    )
}
