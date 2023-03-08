package com.seanshubin.game_4x.language

class ZeroOrMore(val name:String, val expression:Expression):Expression {
    override fun consume(cursor: Cursor<Char>): Result {
        val (newCursor, list) = ExpressionUtil.consumeMany(cursor, expression)
        return Result.success(newCursor, Branch(name, list))
    }
}
