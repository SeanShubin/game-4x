package com.seanshubin.game_4x.language

data class SeqOf(val name:String, val expressions: List<Expression>) : Expression {
    constructor(name:String, vararg expression: Expression) : this(name, expression.toList())

    override fun consume(cursor: Cursor<Char>): Result {
        var currentCursor = cursor
        val list = mutableListOf<Tree<Char>>()
        expressions.forEach { expression ->
            val result = expression.consume(currentCursor)
            if (result.success) {
                currentCursor = result.cursor
                list.add(result.tree)
            } else {
                return Result.failure(cursor)
            }
        }
        return Result.success(currentCursor, Branch(name, list))
    }
}
