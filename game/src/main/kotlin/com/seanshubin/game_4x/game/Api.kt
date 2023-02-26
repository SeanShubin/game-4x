package com.seanshubin.game_4x.game

interface Api {
    fun listPlanets():List<Planet>
    fun createPlanet(name:String)
    fun listLands(planetName:String):List<Land>
    fun createLand(planetName:String)
    fun getLand(planetName:String, landIndex:Int):Land
    fun addThing(planetName:String, landIndex:Int, thing:Thing, quantity:Int = 1)
    fun colonize(planetName:String, landIndex:Int, name:String)
}
