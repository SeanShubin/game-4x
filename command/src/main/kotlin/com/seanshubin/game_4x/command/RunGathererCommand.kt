package com.seanshubin.game_4x.command

import arrow.core.Either
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Things

data class RunGathererCommand(val resource:String, val density:Int) : LandCommand {
    override fun toObject(): String = this.javaClass.simpleName

    override fun execute(land: Land): Either<Failure, Land> {
        DebugCommand.debug(this)
        val unusedGatherer = Things.createGatherer(resource)
        val usedGatherer = Things.createGatherer(resource, activated = true)
        val unusedNode = Things.createNode(resource, density)
        val usedNode = Things.createNode(resource, density, activated = true)
        val unusedCitizen = Things.createCitizen()
        val usedCitizen = Things.createCitizen(activated = true)
        val supply = Things.createSupply(resource)

        val composite = CompositeLandCommand(
            RemoveCommand(unusedGatherer),
            AddCommand(usedGatherer),
            RemoveCommand(unusedNode),
            AddCommand(usedNode),
            RemoveCommand(unusedCitizen),
            AddCommand(usedCitizen),
            AddCommand(supply, density)
        )

        return composite.execute(land)
    }
}
