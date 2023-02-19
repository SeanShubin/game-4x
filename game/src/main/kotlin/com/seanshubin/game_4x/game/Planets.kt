package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.MapUtil.updated

data class Planets(val planetList: List<Planet>) {
//    fun map(f: (Map.Entry<String, Planet>) -> Planet): Planets = Planets(planetMap.mapValues(f))
//    fun hasColony(name: String): Boolean = planetMap.getValue(name).hasColony()
    fun addPlanet(planet:Planet):Planets = copy(planetList = planetList + planet)
//    fun addPlanet(name: String, size: Int, resources: Resources): Planets {
//        val newPlanet = Planet(name, size, resources)
//        val newPlanetMap = planetMap + Pair(newPlanet.name, newPlanet)
//        return copy(planetMap = newPlanetMap)
//    }
//
//    fun addPlanetOrbital(planetName: String, orbitalName: String): Planets {
//        val oldPlanet = planetMap.getValue(planetName)
//        val newPlanet = oldPlanet.addOrbital(orbitalName)
//        return copy(planetMap = planetMap + Pair(planetName, newPlanet))
//    }
//
//    fun fullyDeveloped(): Boolean {
//        return planetMap.values.all { it.fullyDeveloped() }
//    }
//
//    fun names(): List<String> = planetMap.keys.toList().sorted()
//    fun canColonize(planetName: String): Boolean = planetMap.getValue(planetName).canColonize()
//    fun colonizePlanet(planetName: String, landIndex: Int): Planets = Planets(planetMap.updated(planetName) { planet ->
//        planet.colonize(landIndex)
//    })
//
    fun toObject(): List<Any> = planetList.map {it.toObject()}

    companion object {
        val empty = Planets(emptyList())
    }
}
