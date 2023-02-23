package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.FormatUtil.formatCommand

data class ColonizeCommand(val planetName: String, val landIndex: Int) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex
    )

    override fun execute(game: Game): Result {
        val secureLandCommand = SecureLandCommand(planetName, landIndex)
        val consumeColonizer = ConsumeOrbitalCommand(planetName, landIndex, Names.COLONIZER)
        val payUndevelopedCost = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.UNDEVELOPED)
        val placeGatherer = ProduceGathererCommand(planetName, landIndex, Names.FOOD)
        val name = formatCommand(this, "planet", planetName, "land", landIndex)
        return CompositeCommand(
            name,
            listOf(
                secureLandCommand,
                consumeColonizer,
                payUndevelopedCost,
                placeGatherer
            )
        ).execute(game)
    }
}
