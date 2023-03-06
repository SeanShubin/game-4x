package com.seanshubin.game_4x.prototype2

data class TopCommand(val required:Boolean, val name:String){
    fun toObject():Map<String, Any> = mapOf(
        "required" to required,
        "name" to name
    )
}
