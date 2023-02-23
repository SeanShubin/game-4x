package com.seanshubin.game_4x.game

data class BuildFarmCommand(val planetName: String, val landIndex: Int) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex
    )

    override fun execute(game: Game): Result {
        val payFoodCost = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.PROCESSED)
        val payUndevelopedCost = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.UNDEVELOPED)
        val placeGatherer = ProduceResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.GATHERER)
        return CompositeCommand(
            listOf(
                payFoodCost,
                payUndevelopedCost,
                placeGatherer
            )
        ).execute(game)
    }
}
