package com.seanshubin.game_4x.game

interface  PlanetApi {
    fun addOrbital(orbitalName:String)
    fun canColonize():Boolean
    fun hasColony():Boolean
    fun colonize(landIndex:Int)
}
