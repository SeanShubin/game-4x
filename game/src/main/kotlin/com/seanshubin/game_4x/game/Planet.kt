package com.seanshubin.game_4x.game

data class Planet(
    val name: String,
    val lands: Lands,
    val inOrbit: XList<String>
) {
    constructor(name: String, size: Int, resources: Resources) : this(
        name, lands = Lands(size, resources), inOrbit = XList.emptyList()
    )

    fun addOrbital(name: String): Planet {
        return copy(inOrbit = inOrbit + name)
    }

    fun fullyDeveloped(): Boolean {
        return lands.fullyDeveloped()
    }

    fun hasColony():Boolean = lands.claimedExists()
    fun canColonize(): Boolean = inOrbit.contains(Names.COLONIZER) && lands.nonClaimedExists()
    fun colonize(landIndex: Int): Planet = removeColonizer().claimLand(landIndex)
    fun removeColonizer(): Planet = copy(inOrbit = inOrbit.removeFirstEqual(Names.COLONIZER))
    fun claimLand(landIndex: Int): Planet = copy(lands = lands.claimLand(landIndex))
    fun toObject():Map<String, Any> = mapOf(
        "name" to name,
        "lands" to lands.toObject(),
        "inOrbit" to inOrbit.list
    )
}
