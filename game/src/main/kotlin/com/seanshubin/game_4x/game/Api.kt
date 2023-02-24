package com.seanshubin.game_4x.game

interface Api {
    fun listPlanetNames():List<String>
    fun createPlanet(name:String)
    fun listLands(planetName:String):List<Land>
    fun createLand(planetName:String)
}
