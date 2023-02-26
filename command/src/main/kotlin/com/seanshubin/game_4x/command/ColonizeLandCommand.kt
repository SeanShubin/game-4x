package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing
import com.seanshubin.game_4x.game.Things

object ColonizeLandCommand:SingleLandCommand {
    override fun execute(things: Land): Either<String, Land> {
        val colonizer = Things.createColonizer()
        val foodNodeQuery = Thing(
            "name" to "node",
            "resource" to "food"
        )
        val citizenQuery = Thing("name" to "citizen")

        val removeColonizer = RemoveCommand(colonizer)
        val atLeastOneFoodNode = GreaterThanCommand(foodNodeQuery, 0)
        val noCitizens = EqualToCommand(citizenQuery, 0)
        val addCitizen = AddCommand(Things.createCitizen())
        val addFarm = AddCommand(Things.createGatherer("food"))

        val composite = CompositeCommand(
            removeColonizer,
            atLeastOneFoodNode,
            noCitizens,
            addCitizen,
            addFarm
        )
        return composite.execute(things)
    }
}
