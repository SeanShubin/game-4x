package com.seanshubin.game_4x.game

import com.seanshubin.game_4x.game.ListUtil.updateAtIndex

data class Planet(val name:String, val lands:List<Land> = emptyList()){
    fun addLand():Planet = copy(lands = lands + Land(name, lands.size))
    fun updateLand(landIndex:Int, update:(Land)->Land):Planet = copy(lands = lands.updateAtIndex(landIndex, update))
    fun updateEachLand(update:(Land)->Land):Planet =
        copy(lands = lands.map(update))
}
