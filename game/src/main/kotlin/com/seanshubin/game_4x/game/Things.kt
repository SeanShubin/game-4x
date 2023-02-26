package com.seanshubin.game_4x.game

object Things {
    fun createColonizer(): Thing = Thing("name" to "colonizer")
    fun createNode(resource: String, density: Int, activated: Boolean = false): Thing = Thing(
        "name" to "node",
        "resource" to resource,
        "density" to density,
        "activated" to activated
    )

    fun createGatherer(resource: String, activated: Boolean = false): Thing = Thing(
        "name" to "gatherer",
        "resource" to resource,
        "activated" to activated
    )

    fun createCitizen(activated: Boolean = false): Thing = Thing(
        "name" to "citizen",
        "activated" to activated
    )

    fun createSupply(resource:String): Thing = Thing(
        "name" to "supply",
        "resource" to resource
    )
}
