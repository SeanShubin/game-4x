package com.seanshubin.game_4x.prototype

object RefreshCommand : AllResourcesCommand() {
    override fun toObject(): String = "RefreshCommand"

    override fun executeOnResource(game: Game, planetName: String, landIndex: Int, resourceName: String): Result {
        val spoil = SpoilCommand(planetName, landIndex, resourceName)
        val refreshRaw = RefreshRawCommand(planetName, landIndex, resourceName)
        val name = FormatUtil.formatCommand(this, "planet", planetName, "land", landIndex, "resource", resourceName)
        val result = CompositeCommand(name, listOf(spoil, refreshRaw)).execute(game)
        return result
    }
}