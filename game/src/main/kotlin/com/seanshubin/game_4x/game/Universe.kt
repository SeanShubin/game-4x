package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateAtIndex
import com.seanshubin.game_4x.game.ListUtil.updateWhere

data class Universe(val planets: List<Planet> = emptyList()) {
    val planetsByName = planets.associateBy { it.name }
    fun addPlanet(name: String): Universe = copy(planets = planets + Planet(name))
    fun updatePlanet(planetName: String, update: (Planet) -> Planet): Universe =
        copy(planets = planets.updateWhere({ it.name == planetName }, update))
    fun updateLand(planetName: String, landIndex: Int, update: (Land) -> Land): Universe =
        updatePlanet(planetName) { planet ->
            planet.copy(lands = planet.lands.updateAtIndex(landIndex, update))
        }
}
