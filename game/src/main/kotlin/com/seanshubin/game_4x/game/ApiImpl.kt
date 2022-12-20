package com.seanshubin.game_4x.game

class ApiImpl(private var state:GameState): Api {

    override fun planetNames(): List<String> {
        throw UnsupportedOperationException("not implemented")
    }

    override fun planet(planetName: String): PlanetApi {
        return object:PlanetApi {
            override fun addResource(resource:Resource) {
                state = state.addPlanetResource(planetName, resource)
            }

            override fun addOrbital(orbitalName: String) {
                state = state.addPlanetOrbital(planetName, orbitalName)
            }
        }
    }

    override fun addPlanet(name: String, size: Int) {
        state = state.addPlanet(name, size)
    }

    override fun gameState(): GameState {
        return state
    }
}