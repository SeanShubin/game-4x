package com.seanshubin.game_4x.game

data class Land(val things: List<Thing> = emptyList()){
    fun addThings(newThings:List<Thing>) = copy(things = things + newThings)
}
