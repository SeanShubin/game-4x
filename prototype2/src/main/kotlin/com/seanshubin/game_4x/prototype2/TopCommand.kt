package com.seanshubin.game_4x.prototype2

data class TopCommand(val modifier:String, val name:String){
    fun toObject():Map<String, String> = mapOf(
        "modifier" to modifier,
        "name" to name
    )
}
