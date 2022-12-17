package com.seanshubin.game_4x.domain

interface  PlanetApi {
    fun addResource(name:String, quantity:Int, rate:Int)
    fun addToOrbit(name:String)
}