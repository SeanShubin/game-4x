package com.seanshubin.game_4x.game

data class Universe(val planets:List<String> = emptyList()) {
    fun containsPlanet(name:String):Boolean = planets.contains(name)
    fun addPlanet(name:String):Universe = copy(planets = planets + name)
}