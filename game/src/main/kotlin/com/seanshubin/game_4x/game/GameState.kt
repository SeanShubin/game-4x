package com.seanshubin.game_4x.game

data class GameState(val planets:Planets) {
    fun addPlanet(name:String, size:Int):GameState {
        return copy(planets = planets.addPlanet(name, size))
    }
    fun addPlanetResource(planetName:String, resource:Resource):GameState {
        return copy(planets = planets.addPlanetResource(planetName, resource))
    }
    fun addPlanetOrbital(planetName:String, orbitalName:String):GameState {
        return copy(planets = planets.addPlanetOrbital(planetName, orbitalName))
    }
    companion object {
        val empty = GameState(planets = Planets.empty)
    }
}
