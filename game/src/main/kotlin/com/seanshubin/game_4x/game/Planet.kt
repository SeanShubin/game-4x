package com.seanshubin.game_4x.game

data class Planet(val name:String, val lands:List<Land> = emptyList()){
    fun addLand():Planet = copy(lands = lands + Land())
}
