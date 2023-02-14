package com.seanshubin.game_4x.game

data class Resources(val resourceList:List<Resource>){
    companion object {
        val empty = Resources(emptyList())
    }
}
