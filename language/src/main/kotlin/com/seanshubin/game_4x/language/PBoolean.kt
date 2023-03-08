package com.seanshubin.game_4x.language

data class PBoolean(val booleanValue:Boolean):Primitive{
    companion object {
        fun Boolean.toPBoolean():PBoolean = PBoolean(this)
    }
}

