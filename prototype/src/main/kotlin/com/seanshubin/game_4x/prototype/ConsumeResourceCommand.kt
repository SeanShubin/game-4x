package com.seanshubin.game_4x.prototype

data class ConsumeResourceCommand(
    val planetName: String,
    val landIndex: Int,
    val resourceName: String,
    val resourceLocation: ResourceLocation
) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex,
        "resourceName" to resourceName,
        "resourceLocation" to resourceLocation
    )

    override fun execute(game: Game): Result {
        val resource = game.resource(planetName, landIndex, resourceName)
        val oldValue = resource.valueAtLocation(resourceLocation)
        val newValue = oldValue - 1
        val name = FormatUtil.formatCommand(this, "planet", planetName, "land", landIndex, "resource", resourceName, "location", resourceLocation)
        return if (newValue < 0) {
            Result(this, game, success = false, listOf(
                name, "$oldValue resources available"))
        } else {
            Result(
                this,
                game.setResourceAtLocation(planetName, landIndex, resourceName, resourceLocation, newValue),
                success = true,
                listOf(name, "changed from $oldValue to $newValue")
            )
        }
    }
}