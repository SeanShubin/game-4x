package com.seanshubin.game_4x.command

import arrow.core.Either
import arrow.core.left
import com.seanshubin.game_4x.game.Land
import com.seanshubin.game_4x.game.Thing
import com.seanshubin.game_4x.game.Things

data class RunGathererCommand(val resource: String) : LandCommand {
    override fun toObject(): String = this.javaClass.simpleName

    override fun execute(land: Land): Either<LandFailure, LandSuccess> {
        val unusedNodes = land.findExpanded(Thing(
            "name" to "node",
            "resource" to resource,
            "activated" to false
        )).sortedByDescending { it.attributeByName.getValue("density").requireInt() }
        val unusedNode = unusedNodes.firstOrNull() ?: return LandFailure(this, land, "no available gatherers").left()
        val density = unusedNode.attributeByName.getValue("density").requireInt()
        val usedNode = unusedNode.setBooleanValue("activated", true)
        val unusedGatherer = Things.createGatherer(resource)
        val usedGatherer = Things.createGatherer(resource, activated = false)
        val unusedCitizen = Things.createCitizen()
        val usedCitizen = Things.createCitizen(activated = true)
        val supply = Things.createSupply(resource)
        val composite = CompositeLandCommand(
            this,
            listOf(
                RemoveCommand(unusedGatherer),
                AddCommand(usedGatherer),
                RemoveCommand(unusedNode),
                AddCommand(usedNode),
                RemoveCommand(unusedCitizen),
                AddCommand(usedCitizen),
                AddCommand(supply, density)
            )
        )
        return composite.execute(land)
    }
}
