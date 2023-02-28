package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing
import com.seanshubin.game_4x.game.Things
import kotlin.math.min

object NewCitizensEnterCommand : LandCommand {
    override fun toObject(): String = this.javaClass.simpleName

    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        val citizen = Things.createCitizen()
        val foodSupply = Things.createSupply("food")
        val totalCitizenCount = land.countPartiallyMatches(Thing("name" to "citizen"))
        val foodSupplyCount = land.quantityByThing[foodSupply] ?: 0
        val newCitizenCount = min(totalCitizenCount, foodSupplyCount)
        return AddCommand(citizen, newCitizenCount).execute(land)
    }
}
