package com.seanshubin.game_4x.game

interface Api {
    fun listPlanets():List<Planet>
    fun createPlanet(name:String)
    fun listLands(planetName:String):List<Land>
    fun createLand(planetName:String)
    fun listThings(planetName:String, landIndex:Int):List<Thing>
    fun createResource(planetName:String, landIndex:Int, name:String, prevalence:Int, density:Int)
}
