package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.Result.Failure
import com.seanshubin.game_4x.language.Result.Success

data class OneOf(val name: String, val expressions: List<Expression>) : Expression {
    constructor(name: String, vararg expression: Expression) : this(name, expression.toList())

    override fun consume(cursor: Cursor<Char>): Result {
        expressions.forEach { expression ->
            when (val result = expression.consume(cursor)) {
                is Success -> return result.wrap(name)
                else -> {}
            }
        }
        return Failure
    }
}
