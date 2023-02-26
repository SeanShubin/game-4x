package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateWhere

data class Land(
    val planetName: String,
    val index: Int,
    val things: List<Pair<Thing, Int>>
) : HasToObject {
    constructor(planetName: String, index: Int, vararg element: Pair<Thing, Int>) : this(
        planetName,
        index,
        element.toList()
    )

    val size: Int get() = things.sumOf { it.second }
    val quantityByThing: Map<Thing, Int> = things.toMap()
    fun countPartiallyMatches(thing: Thing): Int = things.filter {
        thing.partiallyMatches(it.first)
    }.sumOf { it.second }

    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "index" to index,
        "things" to things.map { it.toObject() }
    )

    private fun Pair<Thing, Int>.toObject(): Map<String, Any> = mapOf(
        "thing" to first.toObject(),
        "quantity" to second
    )

    fun toLines(): List<String> = things.flatMap { it.toLines() }
    fun addThing(thing: Thing, quantityToAdd: Int = 1): Land =
        updateQuantity(thing) { oldValue -> oldValue + quantityToAdd }

    fun setQuantity(thing: Thing, newQuantity: Int): Land =
        updateQuantity(thing) { newQuantity }

    private fun updateQuantity(target: Thing, update: (Int) -> Int): Land =
        if (quantityByThing.containsKey(target)) {
            copy(things = things.updateWhere({ (thing, _) -> thing == target }) { (thing, quantity) ->
                thing to update(quantity)
            })
        } else {
            copy(things = things + (target to update(0)))
        }

    companion object {
        fun Pair<Thing, Int>.toLines(): List<String> {
            val (thing, quantity) = this
            return listOf("quantity = $quantity") + thing.toLines().map { "  $it" }
        }
    }
}