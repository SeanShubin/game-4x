package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.Result.Failure
import com.seanshubin.game_4x.language.Result.Success
import com.seanshubin.game_4x.language.Tree.Leaf
import com.seanshubin.game_4x.language.Tree.Branch

data class SeqOf(val name: String, val expressions: List<Expression>) : Expression {
    constructor(name: String, vararg expression: Expression) : this(name, expression.toList())

    override fun consume(cursor: Cursor<Char>): Result {
        var currentCursor = cursor
        val list = mutableListOf<Tree<Char>>()
        expressions.forEach { expression ->
            when (val result = expression.consume(currentCursor)) {
                is Success -> {
                    currentCursor = result.cursor
                    list.add(result.tree)
                }
                is Failure -> {
                    return Failure
                }
            }
        }
        return Success(currentCursor, Branch(name, list))
    }
}
