package com.seanshubin.game_4x.game

class ApiImpl:Api {
    private var universe:Universe = Universe()
    override fun listPlanets(): List<String> {
        return universe.planets
    }

    override fun createPlanet(name: String) {
        universe = CreatePlanetCommand(name).execute(universe).assertSuccess()
    }
}