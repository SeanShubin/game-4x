package com.seanshubin.game_4x.game

data class BuildGatherer(val planetName: String, val landIndex: Int, val resourceName: String) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex,
        "resourceName" to resourceName
    )

    override fun execute(game: Game): Result {
        val payFoodCost = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.PROCESSED)
        val secureNodeCost = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.UNDEVELOPED)
        val placeGatherer = ProduceResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.GATHERER)
        return CompositeCommand(listOf(payFoodCost, secureNodeCost, placeGatherer)).execute(game)
    }
}
