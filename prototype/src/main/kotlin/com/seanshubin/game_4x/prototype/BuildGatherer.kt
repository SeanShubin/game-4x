package com.seanshubin.game_4x.prototype

data class BuildGatherer(val planetName: String, val landIndex: Int, val resourceName: String) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex,
        "resourceName" to resourceName
    )

    override fun execute(game: Game): Result {
        val payFoodCost = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.PROCESSED)
        val secureNodeCost = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.UNDEVELOPED)
        val placeGatherer = ProduceGathererCommand(planetName, landIndex, Names.FOOD)
        val name = FormatUtil.formatCommand(this, "planet", planetName, "land", landIndex, "resource", resourceName)
        return CompositeCommand(name,listOf(payFoodCost, secureNodeCost, placeGatherer)).execute(game)
    }
}