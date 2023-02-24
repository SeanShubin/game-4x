package com.seanshubin.game_4x.prototype

data class SecureLandCommand(val planetName: String, val landIndex: Int) : Command {
    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "landIndex" to landIndex
    )

    override fun execute(game: Game): Result {
        val oldSecurity = game.getSecurity(planetName, landIndex)
        val name = FormatUtil.formatCommand(this, "planet", planetName, "land", landIndex)
        return if (oldSecurity == 0) {
            val newSecurity = 1
            Result(
                this,
                game.setSecurity(planetName, landIndex, newSecurity),
                success = true,
                details = listOf(name, "Set security from $oldSecurity to $newSecurity")
            )
        } else {
            Result(
                this,
                game,
                success = false,
                details = listOf(name, "Security already had value of $oldSecurity")
            )
        }
    }
}