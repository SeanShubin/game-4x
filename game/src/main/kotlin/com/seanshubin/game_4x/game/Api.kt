package com.seanshubin.game_4x.game

interface Api {
    fun listPlanets():List<Planet>
    fun createPlanet(name:String)
    fun listLands(planetName:String):List<Land>
    fun createLand(planetName:String)
    fun listResources(planetName:String, landIndex:Int):List<Resource>
}
