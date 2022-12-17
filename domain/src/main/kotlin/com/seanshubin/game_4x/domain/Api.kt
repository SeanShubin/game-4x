package com.seanshubin.game_4x.domain

interface Api {
    fun planetNames():List<String>
    fun planet(name:String):PlanetApi
    fun addPlanet(name:String, size:Int)
}