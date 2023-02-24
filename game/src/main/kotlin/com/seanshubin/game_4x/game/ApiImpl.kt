package com.seanshubin.game_4x.game

class ApiImpl:Api {
    private var universe:Universe = Universe()
    override fun listPlanetNames(): List<String> =
        universe.planets.map {it.name}

    override fun createPlanet(name: String) {
        universe = CreatePlanetCommand(name).execute(universe).assertSuccess()
    }

    override fun listLands(planetName: String): List<Land> =
        universe.planetsByName.getValue(planetName).lands

    override fun createLand(planetName: String) {
        universe = CreateLandCommand(planetName).execute(universe).assertSuccess()
    }
}