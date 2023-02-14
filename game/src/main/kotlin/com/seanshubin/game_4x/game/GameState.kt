package com.seanshubin.game_4x.game

data class GameState(val turn:Int, val planets:Planets) {
    fun addPlanet(name:String, size:Int, resources:Resources):GameState {
        return copy(planets = planets.addPlanet(name, size, resources))
    }
    fun addPlanetOrbital(planetName:String, orbitalName:String):GameState {
        return copy(planets = planets.addPlanetOrbital(planetName, orbitalName))
    }
    fun hasColony(planetName:String):Boolean = planets.hasColony(planetName)
    fun canColonizePlanet(planetName:String):Boolean = planets.canColonize(planetName)
    fun colonizePlanet(planetName:String, landIndex:Int):GameState {
        return copy(planets = planets.colonizePlanet(planetName, landIndex))
    }
    fun toObject():Map<String, Any> = mapOf(
        "turn" to turn,
        "planets" to planets.toObject()
    )

    companion object {
        val empty = GameState(turn= 0, planets = Planets.empty)
    }
}
