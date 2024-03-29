package com.seanshubin.game_4x.game

class ApiImpl : Api {
    private var universe: Universe = Universe()
    override fun listPlanets(): List<Planet> = universe.planets

    override fun createPlanet(name: String) {
        universe = universe.addPlanet(name)
    }

    override fun listLands(planetName: String): List<Land> =
        universe.getPlanet(planetName).lands

    override fun createLand(planetName: String) {
        universe = universe.updatePlanet(planetName) { it.addLand() }
    }

    override fun getLand(planetName: String, landIndex: Int): Land =
        universe.getPlanet(planetName).lands[landIndex]

    override fun addThing(planetName: String, landIndex: Int, thing: Thing, quantity: Int) {
        universe = universe.updateLand(planetName, landIndex) { it.addThing(thing, quantity) }
    }

    override fun colonize(planetName: String, landIndex: Int, name: String) {
        throw UnsupportedOperationException("not implemented")
    }
}
