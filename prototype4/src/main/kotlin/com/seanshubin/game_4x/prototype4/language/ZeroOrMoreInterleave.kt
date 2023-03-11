package com.seanshubin.game_4x.prototype4.language

import com.seanshubin.game_4x.prototype4.cursor.Cursor
import com.seanshubin.game_4x.prototype4.language.ExpressionUtil.consumeManyInterleave
import com.seanshubin.game_4x.prototype4.language.Result.Success
import com.seanshubin.game_4x.prototype4.language.Tree.Branch

data class ZeroOrMoreInterleave(val name: String, val outer: Expression, val inner: Expression) : Expression {
    override fun consume(cursor: Cursor<Char>): Result {
        val (newCursor, list) = consumeManyInterleave(cursor, outer, inner)
        return Success(newCursor, Branch(name, list))
    }
}
