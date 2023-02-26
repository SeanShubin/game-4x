package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateWhere

data class Land(
    val planetName:String,
    val index:Int,
    val quantities: List<Pair<Thing, Int>>):HasToObject {
    constructor(planetName:String, index:Int, vararg element:Pair<Thing, Int>):this(planetName, index, element.toList())
    val size:Int get() = quantities.sumOf{it.second}
    val quantityByThing: Map<Thing, Int> = quantities.toMap()
    fun countPartiallyMatches(thing:Thing):Int = quantities.filter {
        thing.partiallyMatches(it.first)
    }.sumOf { it.second }

    override fun toObject(): Map<String, Any> = mapOf(
        "planetName" to planetName,
        "index" to index,
        "quantities" to quantities.map{it.toObject()}
    )
    private fun Pair<Thing, Int>.toObject():Map<String, Any> = mapOf(
        "thing" to first.toObject(),
        "quantity" to second
    )
    fun toLines():List<String> = quantities.flatMap{it.toLines()}
    fun addThing(thing:Thing, quantityToAdd:Int = 1):Land =
        updateQuantity(thing){ oldValue -> oldValue + quantityToAdd }

    fun setQuantity(thing:Thing, newQuantity:Int):Land =
        updateQuantity(thing){ newQuantity }

    private fun updateQuantity(target:Thing, update:(Int)->Int):Land =
        if(quantityByThing.containsKey(target)){
            copy(quantities = quantities.updateWhere({ (thing, _) -> thing == target }) { (thing, quantity) ->
                thing to update(quantity)
            })
        } else {
            copy(quantities = quantities + (target to update(0)))
        }

    companion object {
        fun Pair<Thing, Int>.toLines():List<String>{
            val (thing, quantity) = this
            return listOf("quantity = $quantity") + thing.toLines().map{"  $it"}
        }
    }
}