package com.seanshubin.game_4x.prototype

import com.seanshubin.game_4x.prototype.FormatUtil.formatCommand

data class RefreshRawCommand(val planetName: String, val landIndex: Int, val resourceName: String) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex,
        "resourceName" to resourceName
    )

    override fun execute(game: Game): Result {
        val name = formatCommand(this, "planet", planetName, "land", landIndex, "resource", resourceName)
        val details = mutableListOf<String>()
        details.add(name)
        val newGame = game.updateResource(planetName, landIndex, resourceName) { resource: Resource ->
            val gatherers = resource.valuesByLocation.getValue(ResourceLocation.GATHERER)
            val raw = resource.valuesByLocation.getValue(ResourceLocation.RAW)
            val needed = gatherers - raw
            if (needed > 0) {
                details.add("Changed from $raw to $gatherers")
                resource.setValueAtLocation(ResourceLocation.RAW, gatherers)
            } else {
                resource
            }
        }
        return Result(this, newGame, success = true, details)
    }
}