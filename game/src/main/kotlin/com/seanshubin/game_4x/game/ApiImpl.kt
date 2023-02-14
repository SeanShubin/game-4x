package com.seanshubin.game_4x.game

class ApiImpl(private var state:GameState): Api {

    override fun planetNames(): List<String> =
        state.planets.names()

    override fun planet(planetName: String): PlanetApi {
        return object:PlanetApi {
            override fun addOrbital(orbitalName: String) {
                state = state.addPlanetOrbital(planetName, orbitalName)
            }

            override fun canColonize(): Boolean {
                return state.canColonizePlanet(planetName)
            }

            override fun hasColony(): Boolean {
                return state.planets.hasColony(planetName)
            }

            override fun colonize(landIndex:Int) {
                state = state.colonizePlanet(planetName, landIndex)
            }
        }
    }

    override fun addPlanet(name: String, size: Int, resources:Resources) {
        state = state.addPlanet(name, size, resources)
    }

    override fun gameState(): GameState {
        return state
    }

    override fun isGameComplete(): Boolean {
        return state.planets.fullyDeveloped()
    }
}