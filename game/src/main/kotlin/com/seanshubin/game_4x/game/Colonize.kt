package com.seanshubin.game_4x.game

object Colonize:AllPlanetsCommand() {
    override fun executeOnPlanet(planet: Planet): Planet? =
        planet.colonize()
}
