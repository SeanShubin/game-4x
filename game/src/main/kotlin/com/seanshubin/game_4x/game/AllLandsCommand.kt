package com.seanshubin.game_4x.game

abstract class AllLandsCommand:AllPlanetsCommand() {
    override fun executeOnPlanet(planet: Planet): Planet? {
        val newLands = planet.lands.landList.map(::executeOnLandNotNull)
        return planet.copy(lands = Lands(newLands))
    }
    private fun executeOnLandNotNull(land:Land):Land =
        executeOnLand(land) ?: land
    abstract fun executeOnLand(land:Land):Land?
}
