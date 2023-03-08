package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.ExpressionUtil.consumeMany

data class OneOrMore(val name: String, val expression: Expression) : Expression {
    override fun consume(cursor: Cursor<Char>): Result {
        val (newCursor, list) = consumeMany(cursor, expression)
        return if (list.isEmpty()) Result.failure()
        else Result.success(newCursor, Branch(name, list))
    }
}
