package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.Result.Success

class ZeroOrMore(val name: String, val expression: Expression) : Expression {
    override fun consume(cursor: Cursor<Char>): Result {
        val (newCursor, list) = ExpressionUtil.consumeMany(cursor, expression)
        return Success(newCursor, Branch(name, list))
    }
}
