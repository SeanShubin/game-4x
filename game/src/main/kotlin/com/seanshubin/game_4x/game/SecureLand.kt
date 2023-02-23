package com.seanshubin.game_4x.game

data class SecureLand(val planetName: String, val landIndex: Int) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex
    )

    override fun execute(game: Game): Result {
        val oldSecurity = game.getSecurity(planetName, landIndex)
        return if (oldSecurity == 0) {
            Result(
                this,
                game.setSecurity(planetName, landIndex, 1),
                success = true,
                details = listOf("Secured planet $planetName land $landIndex")
            )
        } else {
            Result(
                this,
                game,
                success = false,
                details = listOf("Planet $planetName land $landIndex already had security value of $oldSecurity")
            )
        }
    }
}
