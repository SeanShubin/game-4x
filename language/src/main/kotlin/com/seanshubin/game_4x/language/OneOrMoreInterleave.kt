package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.ExpressionUtil.consumeManyInterleave
import com.seanshubin.game_4x.language.Result.Failure
import com.seanshubin.game_4x.language.Result.Success
import com.seanshubin.game_4x.language.Tree.Leaf
import com.seanshubin.game_4x.language.Tree.Branch

data class OneOrMoreInterleave(val name: String, val outer: Expression, val inner: Expression) : Expression {
    override fun consume(cursor: Cursor<Char>): Result {
        val (newCursor, list) = consumeManyInterleave(cursor, outer, inner)
        return if (list.isEmpty()) Failure
        else Success(newCursor, Branch(name, list))
    }
}
