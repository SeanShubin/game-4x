package com.seanshubin.game_4x.language

data class StringOf(val name:String, val value:String):Expression {
    override fun consume(cursor: Cursor<Char>): Result =
        SeqOf(name, value.map { ch ->
            CharOf(name, ch)
        }).consume(cursor)
}
