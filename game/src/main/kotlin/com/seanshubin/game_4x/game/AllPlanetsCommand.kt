package com.seanshubin.game_4x.game

abstract class AllPlanetsCommand:Command {
    override fun execute(game: Game): Game {
        throw UnsupportedOperationException("not implemented")
//        val newPlanets = game.planets.map{ (_, planet) ->
//            executeOnPlanet(planet)
//        }
//        return game.copy(planets = newPlanets)
    }
    abstract fun executeOnPlanet(planet:Planet):Planet?
}