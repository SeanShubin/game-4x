package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateWhere

data class CollectionOfThings(val quantities: List<Pair<Thing, Int>>) {
    constructor(vararg element:Pair<Thing, Int>):this(element.toList())
    val size:Int get() = quantities.sumOf{it.second}
    val quantityByThing = quantities.toMap()
    fun toLines():List<String> = quantities.flatMap{it.toLines()}
    fun changeQuantity(thing: Thing, delta: Int): CollectionOfThings {
        val oldQuantity = quantityByThing[thing] ?: 0
        val newQuantity = oldQuantity + delta
        if (newQuantity < 0) throw RuntimeException("for $thing of quantity $oldQuantity, can't change by $delta because that would result in negative number $newQuantity")
        return if (newQuantity == 0) copy(quantities = quantities.filter { (key, _) -> key != thing })
        else if (oldQuantity == 0) copy(quantities = quantities + (thing to newQuantity))
        else copy(quantities = quantities.updateWhere({ (first, _) -> first == thing }) { (first, _) ->
            first to newQuantity
        })
    }
    companion object {
        fun Pair<Thing, Int>.toLines():List<String>{
            val (thing, quantity) = this
            return listOf("quantity = $quantity") + thing.toLines().map{"  $it"}
        }
    }
}