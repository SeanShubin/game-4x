package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.applyToExactlyOneApplicable

data class Game(val planets: List<Planet> = emptyList()) {
    private val planetMap = planets.associateBy { it.name }
    fun containsOrbital(planetName: String, landIndex: Int, orbitalName: String): Boolean =
        oribtal(planetName, landIndex).contains(orbitalName)

    fun resourceNames(planetName: String, landIndex: Int): List<String> =
        landAt(planetName, landIndex).resources.getNames()

    fun getSecurity(planetName: String, landIndex: Int): Int = planetMap.getValue(planetName).getSecurity(landIndex)
    fun setSecurity(planetName: String, landIndex: Int, newSecurityValue: Int): Game =
        updateLand(planetName, landIndex) { land: Land ->
            land.setSecurity(newSecurityValue)
        }

    fun landAt(planetName: String, landIndex: Int): Land = planetMap.getValue(planetName).land(landIndex)
    fun addPlanet(name: String): Game = copy(planets = planets + Planet(name))
    fun addLand(planetName: String, quantity: Int): Game {
        val nameMatches = Planet.nameMatches(planetName)
        val addLand = { planet: Planet -> planet.addLand(quantity) }
        val newPlanets = planets.applyToExactlyOneApplicable(nameMatches, addLand)
        return copy(planets = newPlanets)
    }

    fun addResource(planetName: String, landIndex: Int, resourceName: String, prevalence: Int, density: Int): Game =
        updatePlanet(planetName) { planet ->
            planet.addResource(landIndex, resourceName, prevalence, density)
        }

    fun addOrbital(planetName: String, landIndex: Int, orbitalName: String): Game =
        updateLand(planetName, landIndex) { land ->
            land.addOrbital(orbitalName)
        }

    fun takeOrbital(planetName: String, landIndex: Int, orbitalName: String): Game =
        updateLand(planetName, landIndex) { land ->
            land.takeOrbital(orbitalName)
        }

    fun updatePlanet(planetName: String, update: (Planet) -> Planet): Game =
        copy(planets = planets.applyToExactlyOneApplicable(Planet.nameMatches(planetName), update))

    fun updateLand(planetName: String, landIndex: Int, updateLand: (Land) -> Land): Game =
        updatePlanet(planetName) { planet: Planet ->
            planet.updateLand(landIndex, updateLand)
        }

    fun updateResource(
        planetName: String,
        landIndex: Int,
        resourceName: String,
        updateResource: (Resource) -> Resource
    ): Game =
        updateLand(planetName, landIndex) { land: Land ->
            land.updateResource(resourceName, updateResource)
        }

    fun planet(name: String): Planet = planetMap.getValue(name)
    fun land(planetName: String, landIndex: Int): Land = planet(planetName).land(landIndex)
    fun oribtal(planetName: String, landIndex: Int): Orbit = planet(planetName).land(landIndex).orbit
    fun resource(planetName: String, landIndex: Int, resourceName: String): Resource =
        land(planetName, landIndex).resource(resourceName)

    fun planetNames(): List<String> = planets.map { it.name }
    fun landCount(planetName: String): Int = planet(planetName).landCount()
    fun setResourceAtLocation(
        planetName: String,
        landIndex: Int,
        resourceName: String,
        resourceLocation: ResourceLocation,
        newValue: Int
    ): Game {
        val nameMatches = Planet.nameMatches(planetName)
        val updatePlanet = { planet: Planet ->
            planet.setResourceAtLocation(landIndex, resourceName, resourceLocation, newValue)
        }
        val newPlanets = planets.applyToExactlyOneApplicable(nameMatches, updatePlanet)
        return copy(planets = newPlanets)
    }

    fun toObject(): Map<String, Any> = mapOf(
        "planets" to planets.map { it.toObject() }
    )
}
