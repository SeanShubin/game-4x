package com.seanshubin.game_4x.game

data class Game(val turn:Int, val planets:List<Planet>) {
    fun setPlanets(planets:List<Planet>):Game = copy(planets = planets)
    fun toObject():Map<String, Any> = mapOf(
        "turn" to turn,
        "planets" to planets.map{it.toObject()}
    )
    companion object {
        val empty = Game(turn= 0, planets = emptyList())
    }
}
