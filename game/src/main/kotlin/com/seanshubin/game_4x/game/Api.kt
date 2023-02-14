package com.seanshubin.game_4x.game

interface Api {
    fun planetNames():List<String>
    fun planet(planetName:String): PlanetApi
    fun addPlanet(planetName:String, size:Int, resources:Resources)
    fun isGameComplete():Boolean
    fun gameState():GameState
}