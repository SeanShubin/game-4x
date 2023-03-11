package com.seanshubin.game_4x.prototype4.language

import com.seanshubin.game_4x.prototype4.language.ExpressionUtil.consumeMany
import com.seanshubin.game_4x.prototype4.language.Result.Success
import com.seanshubin.game_4x.prototype4.language.Tree.Branch
import com.seanshubin.game_4x.prototype4.cursor.Cursor

class ZeroOrMore(val name: String, val expression: Expression) : Expression {
    override fun consume(cursor: Cursor<Char>): Result {
        val (newCursor, list) = consumeMany(cursor, expression)
        return Success(newCursor, Branch(name, list))
    }
}
