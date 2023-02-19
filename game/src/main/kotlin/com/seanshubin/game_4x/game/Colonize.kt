package com.seanshubin.game_4x.game

class Colonize:AllPlanetsCommand() {
    override fun executeOnPlanet(planet: Planet): Planet {
        return planet
    }
}