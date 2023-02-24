package com.seanshubin.game_4x.game

interface Api {
    fun listPlanets():List<String>
    fun createPlanet(name:String)
}