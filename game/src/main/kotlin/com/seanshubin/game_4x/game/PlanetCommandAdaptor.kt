package com.seanshubin.game_4x.game

class PlanetCommandAdaptor(val planetCommand:(Planet)->Planet):Command {
    override fun execute(game: Game): Game {
        val newPlanets = game.planets.map(planetCommand)
        return game.copy(planets = newPlanets)
    }
}
