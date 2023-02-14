package com.seanshubin.game_4x.game

class StrategyImpl:Strategy {
    override fun takeTurn(game: GameState): GameState {
        val newPlanets = game.planets.map(::takeTurnForPlanet)
        return game.copy(planets = newPlanets)
    }
    private fun takeTurnForPlanet(entry:Map.Entry<String, Planet>):Planet {
        val planet = entry.value
        return if(!planet.hasColony() && planet.canColonize()){
            val landIndex = 0
            planet.colonize(landIndex)
        } else {
            planet
        }
    }
}
