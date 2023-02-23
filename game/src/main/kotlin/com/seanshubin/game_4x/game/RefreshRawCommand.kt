package com.seanshubin.game_4x.game

data class RefreshRawCommand(val planetName: String, val landIndex: Int, val resourceName: String) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex,
        "resourceName" to resourceName
    )

    override fun execute(game: Game): Result {
        val details = mutableListOf<String>()
        val newGame = game.updateResource(planetName, landIndex, resourceName) { resource: Resource ->
            val gatherers = resource.valuesByLocation.getValue(ResourceLocation.GATHERER)
            val raw = resource.valuesByLocation.getValue(ResourceLocation.RAW)
            val needed = gatherers - raw
            if (needed > 0) {
                details.add("Refreshed $needed raw $resourceName from $raw to $gatherers on planet $planetName land $landIndex")
                resource.setValueAtLocation(ResourceLocation.RAW, gatherers)
            } else {
                resource
            }
        }
        return Result(this, newGame, success = true, details)
    }
}
