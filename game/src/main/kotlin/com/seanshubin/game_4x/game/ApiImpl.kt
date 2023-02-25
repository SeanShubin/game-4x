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

    override fun listThings(planetName: String, landIndex: Int): CollectionOfThings =
        universe.planetsByName.getValue(planetName).lands[landIndex].things

    override fun addThing(planetName: String, landIndex: Int, thing: Thing, quantity: Int) {
        universe = CreateThingCommand(planetName, landIndex, thing, quantity).execute(universe)
    }
}
