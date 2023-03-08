package com.seanshubin.game_4x.language

data class OneOf(val name:String, val expressions:List<Expression>):Expression {
    constructor(name:String, vararg expression:Expression):this(name, expression.toList())

    override fun consume(cursor: Cursor<Char>): Result {
        expressions.forEach { expression ->
            val result = expression.consume(cursor)
            if(result.success) {
                return Result.wrap(name, result)
            }
        }
        return Result.failure(cursor)
    }
}
