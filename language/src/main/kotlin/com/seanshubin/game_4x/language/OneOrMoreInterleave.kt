package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.ExpressionUtil.consumeManyInterleave

data class OneOrMoreInterleave(val name: String, val outer: Expression, val inner: Expression) : Expression {
    override fun consume(cursor: Cursor<Char>): Result {
        val (newCursor, list) = consumeManyInterleave(cursor, outer, inner)
        return if (list.isEmpty()) Result.failure()
        else Result.success(newCursor, Branch(name, list))
    }
}
