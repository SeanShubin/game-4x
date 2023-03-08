package com.seanshubin.game_4x.language

object Parser {
    fun parseExpression(expression:Expression, cursor:Cursor<Char>):Result {
        return expression.consume(cursor)
    }
}