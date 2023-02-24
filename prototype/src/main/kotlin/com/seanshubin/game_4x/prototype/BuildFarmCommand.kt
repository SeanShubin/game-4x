package com.seanshubin.game_4x.prototype

import com.seanshubin.game_4x.prototype.FormatUtil.formatCommand

data class BuildFarmCommand(val planetName: String, val landIndex: Int) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex
    )

    override fun execute(game: Game): Result {
        val payFoodCost = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.PROCESSED)
        val payUndevelopedCost = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.UNDEVELOPED)
        val placeGatherer = ProduceGathererCommand(planetName, landIndex, Names.FOOD)
        val name = formatCommand(this, "planet", planetName, "land", landIndex)
        return CompositeCommand(
            name,
            listOf(
                payFoodCost,
                payUndevelopedCost,
                placeGatherer
            )
        ).execute(game)
    }
}