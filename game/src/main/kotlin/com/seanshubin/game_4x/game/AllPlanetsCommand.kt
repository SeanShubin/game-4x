package com.seanshubin.game_4x.game

abstract class AllPlanetsCommand:Command {
    override fun execute(game: Game): Game =
        game.copy(planets= game.planets.map(::executeOnPlanetNotNull))
    private fun executeOnPlanetNotNull(planet:Planet):Planet =
        executeOnPlanet(planet) ?: planet
    abstract fun executeOnPlanet(planet:Planet):Planet?
}
