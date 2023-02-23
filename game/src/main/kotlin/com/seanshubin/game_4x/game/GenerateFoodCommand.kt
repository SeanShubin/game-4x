package com.seanshubin.game_4x.game

data class GenerateFoodCommand(val planetName: String, val landIndex: Int) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex
    )

    override fun execute(game: Game): Result {
        val consumeRaw = ConsumeResourceCommand(planetName, landIndex, Names.FOOD, ResourceLocation.RAW)
        val produceProcessed = ProduceResourceCommand(planetName, landIndex, Names.FOOD)
        val name = FormatUtil.formatCommand(this, "planet", planetName, "land", landIndex)
        return CompositeCommand(
            name,
            listOf(
                consumeRaw,
                produceProcessed,
            )
        ).execute(game)
    }
}