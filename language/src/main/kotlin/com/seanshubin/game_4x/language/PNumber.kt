package com.seanshubin.game_4x.language

data class PNumber(val intValue:Int):Primitive{
    companion object {
        fun Int.toPNumber():PNumber = PNumber(this)
    }
}
