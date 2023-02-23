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
        return if (game.containsOrbital(planetName, landIndex, orbitalName)) {
            val newGame = game.takeOrbital(planetName, landIndex, orbitalName)
            Result(
                this, newGame, success = true, listOf(
                    "Removed orbital $orbitalName from planet $planetName land $landIndex"
                )
            )
        } else {
            Result(
                this, game, success = false, listOf(
                    "No orbital $orbitalName available on planet $planetName land $landIndex"
                )
            )
        }
    }
}
