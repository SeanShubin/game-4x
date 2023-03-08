package com.seanshubin.game_4x.language

interface Expression {
    fun consume(cursor: Cursor<Char>): Result
}
