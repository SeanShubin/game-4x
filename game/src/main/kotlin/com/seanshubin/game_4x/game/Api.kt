package com.seanshubin.game_4x.game

interface Api {
    fun listPlanets():List<Planet>
    fun createPlanet(name:String)
    fun listLands(planetName:String):List<Land>
    fun createLand(planetName:String)
    fun listThings(planetName:String, landIndex:Int):CollectionOfThings
    fun addThing(planetName:String, landIndex:Int, thing:Thing, quantity:Int = 1)
}
