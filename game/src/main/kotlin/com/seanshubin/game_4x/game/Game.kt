package com.seanshubin.game_4x.game

data class Game(val turn:Int, val planets:Planets) {
    fun addPlanet(planet:Planet):Game = copy(planets = planets.addPlanet(planet))
    fun toObject():Map<String, Any> = mapOf(
        "turn" to turn,
        "planets" to planets.toObject()
    )
    companion object {
        val empty = Game(turn= 0, planets = Planets.empty)
    }
}
