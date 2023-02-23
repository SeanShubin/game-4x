package com.seanshubin.game_4x.game

data class SpoilCommand(val planetName: String, val landIndex: Int, val resourceName: String) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex,
        "resourceName" to resourceName
    )

    override fun execute(game: Game): Result {
        val name = FormatUtil.formatCommand(this, "planet", planetName, "land", landIndex, "resource", resourceName)
        val details = mutableListOf<String>()
        details.add(name)
        val newGame = game.updateResource(planetName, landIndex, resourceName) { resource: Resource ->
            val processed = resource.valuesByLocation.getValue(ResourceLocation.PROCESSED)
            if (processed == 0) {
                resource
            } else {
                details.add("Changed from $processed to 0")
                resource.setValueAtLocation(ResourceLocation.PROCESSED, 0)
            }
        }
        return Result(this, newGame, success = true, details)
    }
}
