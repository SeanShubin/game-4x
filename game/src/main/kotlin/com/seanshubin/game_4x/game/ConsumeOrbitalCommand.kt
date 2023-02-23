package com.seanshubin.game_4x.game

data class ConsumeOrbitalCommand(
    val planetName: String,
    val landIndex: Int,
    val orbitalName: String
) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex,
        "orbitalName" to orbitalName
    )

    override fun execute(game: Game): Result {
        val name = FormatUtil.formatCommand(this, "planet", planetName, "land", landIndex, "orbital", orbitalName)
        return if (game.containsOrbital(planetName, landIndex, orbitalName)) {
            val newGame = game.takeOrbital(planetName, landIndex, orbitalName)
            Result(
                this, newGame, success = true, listOf(
                    name,
                    "Removed orbital"
                )
            )
        } else {
            Result(
                this, game, success = false, listOf(
                    name,
                    "Orbital not available"
                )
            )
        }
    }
}
