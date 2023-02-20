package com.seanshubin.game_4x.game

data class Game(val planets:List<Planet> = emptyList()) {
    fun endTurn():Game = copy(planets = planets.map{it.endTurn()})
    fun toObject():Map<String, Any> = mapOf(
        "planets" to planets.map{it.toObject()}
    )
}
