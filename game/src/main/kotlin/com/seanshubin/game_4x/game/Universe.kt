package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateExactlyOneWhere

data class Universe(val planets:List<Planet> = emptyList()) {
    val planetsByName = planets.associateBy { it.name }
    fun containsPlanet(name:String):Boolean = planetsByName.containsKey(name)
    fun addPlanet(name:String):Universe = copy(planets = planets + Planet(name))
    fun addLand(planetName:String):Universe = updatePlanet(planetName){ planet ->
        planet.addLand()
    }
    private fun updatePlanet(planetName:String, update:(Planet)->Planet):Universe =
        copy(planets = planets.updateExactlyOneWhere({it.name == planetName}, update))
}
