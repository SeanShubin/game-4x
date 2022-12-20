package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.LinkedHashMapUtil.plus

data class Planets(val planetMap:LinkedHashMap<String, Planet>) {
    fun addPlanet(name:String, size:Int):Planets {
        val newPlanet = Planet(name, size)
        val newPlanetMap = planetMap + Pair(newPlanet.name, newPlanet)
        return copy(planetMap = newPlanetMap)
    }
    fun addPlanetResource(planetName:String, resource:Resource):Planets {
        val oldPlanet = planetMap.getValue(planetName)
        val newPlanet = oldPlanet.addResource(resource)
        return copy(planetMap = planetMap + Pair(planetName, newPlanet))
    }
    fun addPlanetOrbital(planetName:String, orbitalName:String):Planets {
        val oldPlanet = planetMap.getValue(planetName)
        val newPlanet = oldPlanet.addOrbital(orbitalName)
        return copy(planetMap = planetMap + Pair(planetName, newPlanet))
    }
    companion object {
        val empty = Planets(LinkedHashMap())
    }
}
