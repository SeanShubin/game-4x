package com.seanshubin.game_4x.game

class StrategyImpl(private val api:Api):Strategy {
    override fun takeTurn(game: GameState): GameState {
        val newPlanets = game.planets.map(::takeTurnForPlanet)
        return game.copy(planets = newPlanets)
    }
    private fun takeTurnForPlanet(planet:Planet):Planet {
        return if(!planet.hasColony() && planet.canColonize()){
            val landIndex = 0
            planet.colonize(landIndex)
        } else {
            planet
        }
    }
}