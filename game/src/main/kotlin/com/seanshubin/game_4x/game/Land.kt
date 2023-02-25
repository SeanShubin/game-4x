package com.seanshubin.game_4x.game

data class Land(val things: CollectionOfThings = CollectionOfThings(emptyList())){
    fun addThing(newThing:Thing, quantity:Int = 1) = copy(things = things.changeQuantity(newThing, quantity))
}
