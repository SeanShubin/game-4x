package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateWhere

data class Land(
    val things: List<Pair<Thing, Int>>
) : HasToObject {
    constructor(vararg element: Pair<Thing, Int>) : this(
        element.toList()
    )

    val size: Int get() = things.sumOf { it.second }
    val quantityByThing: Map<Thing, Int> = things.toMap()
    fun fullMatchesFor(query: Thing):List<Thing> =
        things.map{it.first}.filter {
            query.isPartOf(it)
        }

    fun countPartiallyMatches(query: Thing): Int = things.filter {
        query.isPartOf(it.first)
    }.sumOf { it.second }

    override fun toObject(): List<Any> = things.map { it.toObject() }

    private fun Pair<Thing, Int>.toObject(): Map<String, Any> = mapOf(
        "thing" to first.toObject(),
        "quantity" to second
    )

    fun toLines(): List<String> = things.flatMap { it.toLines() }
    fun addThing(thing: Thing, quantityToAdd: Int = 1): Land =
        updateQuantity(thing) { oldValue -> oldValue + quantityToAdd }

    fun setQuantity(thing: Thing, newQuantity: Int): Land =
        updateQuantity(thing) { newQuantity }

    private fun updateQuantity(target: Thing, update: (Int) -> Int): Land {
        val existingValue = quantityByThing[target]
        val newValue = if (existingValue == null) {
            update(0)
        } else {
            update(existingValue)
        }
        return if (existingValue == null) {
            if (newValue == 0) {
                this
            } else {
                copy(things = things + (target to newValue))
            }
        } else {
            if (newValue == 0) {
                copy(things = things.filter { (thing, _) -> thing != target })
            } else {
                copy(things = things.updateWhere({ (thing, _) -> thing == target }) { (thing, _) ->
                    thing to newValue
                })
            }
        }
    }

    companion object {
        fun Pair<Thing, Int>.toLines(): List<String> {
            val (thing, quantity) = this
            return listOf("quantity = $quantity") + thing.toLines().map { "  $it" }
        }
    }
}
