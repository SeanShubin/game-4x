package com.seanshubin.game_4x.game

class ApiImpl:Api {
    private var universe:Universe = Universe()
    override fun listPlanets(): List<Planet> = universe.planets

    override fun createPlanet(name: String) {
        universe = CreatePlanetCommand(name).execute(universe)
    }

    override fun listLands(planetName: String): List<Land> =
        universe.planetsByName.getValue(planetName).lands

    override fun createLand(planetName: String) {
        universe = CreateLandCommand(planetName).execute(universe)
    }

    override fun listThings(planetName: String, landIndex: Int): List<Thing> =
        universe.planetsByName.getValue(planetName).lands[landIndex].things

    override fun createResource(planetName: String, landIndex: Int, name: String, prevalence: Int, density: Int) {
        universe = CreateResourceCommand(planetName, landIndex, name, prevalence, density).execute(universe)
    }
}
