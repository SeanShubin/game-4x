package com.seanshubin.game_4x.domain

object SampleGameApp {
    @JvmStatic
    fun main(args: Array<String>) {
        val api: Api = ApiImpl()
        api.addPlanet("Planet A", size = 10)
        val planetA = api.planet("Planet A")
        api.addPlanet("Planet B", size = 6)
        planetA.addResource("food", quantity = 4, rate = 4)
        planetA.addResource("material", quantity = 2, rate = 7)
        planetA.addResource("energy", quantity = 6, rate = 5)
        val planetB = api.planet("Planet B")
        planetB.addResource("food", quantity = 2, rate = 3)
        planetB.addResource("material", quantity = 8, rate = 6)
        planetB.addResource("energy", quantity = 3, rate = 4)
        planetA.addToOrbit("colonizer")
    }
}
