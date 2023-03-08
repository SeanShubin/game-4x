package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.ExpressionUtil.consumeManyInterleave

data class ZeroOrMoreInterleave(val name: String, val outer: Expression, val inner:Expression) : Expression {
    override fun consume(cursor: Cursor<Char>): Result {
        val (newCursor, list) = consumeManyInterleave(cursor, outer, inner)
        return Result.success(newCursor, Branch(name, list))
    }
}
