package com.seanshubin.game_4x.prototype

data class ProduceGathererCommand(
    val planetName: String,
    val landIndex: Int,
    val resourceName: String
) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex,
        "resourceName" to resourceName
    )

    override fun execute(game: Game): Result {
        val name = FormatUtil.formatCommand(
            this,
            "planet",
            planetName,
            "land",
            landIndex,
            "resource",
            resourceName
        )
        val resource = game.resource(planetName, landIndex, resourceName)
        val oldValue = resource.valueAtLocation(ResourceLocation.GATHERER)
        val newValue = oldValue + 1
        return Result(
            this,
            game.setResourceAtLocation(planetName, landIndex, resourceName, ResourceLocation.GATHERER, newValue),
            success = true,
            listOf(name, "changed from $oldValue to $newValue")
        )
    }
}