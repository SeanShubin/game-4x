package com.seanshubin.game_4x.language

data class PString(val stringValue: String) : Primitive {
    companion object {
        fun String.toPString(): PString = PString(this)
    }
}
