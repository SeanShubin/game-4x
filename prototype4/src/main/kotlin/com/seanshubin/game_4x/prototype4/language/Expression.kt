package com.seanshubin.game_4x.prototype4.language
import com.seanshubin.game_4x.prototype4.cursor.Cursor

interface Expression {
    fun consume(cursor: Cursor<Char>): Result
}
