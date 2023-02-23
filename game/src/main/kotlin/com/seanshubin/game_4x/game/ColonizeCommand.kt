package com.seanshubin.game_4x.game

data class ColonizeCommand(val planetName: String, val landIndex: Int) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex
    )

    override fun execute(game: Game): Result {
        val secureLand = SecureLand(planetName, landIndex)
        val consumeColonizer = ConsumeOrbitalCommand(planetName, landIndex, Names.COLONIZER)
        val payUndevelopedCost = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.UNDEVELOPED)
        val placeGatherer = ProduceResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.GATHERER)
        return CompositeCommand(
            listOf(
                secureLand,
                consumeColonizer,
                payUndevelopedCost,
                placeGatherer
            )
        ).execute(game)
    }
}
