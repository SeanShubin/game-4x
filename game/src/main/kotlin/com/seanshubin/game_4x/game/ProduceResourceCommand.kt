package com.seanshubin.game_4x.game

data class ProduceResourceCommand(
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
        val newValue = oldValue + 1
        return if (newValue < 0) {
            val message = "$oldValue resources available"
            Result(this, game, success = false, listOf(message))
        } else {
            val message = "Value changed from $oldValue to $newValue"
            Result(
                this,
                game.setResourceAtLocation(planetName, landIndex, resourceName, resourceLocation, newValue),
                success = true,
                listOf(message)
            )
        }
    }
}
