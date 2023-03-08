package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.Result.Failure
import com.seanshubin.game_4x.language.Result.Success

object ExpressionUtil {
    fun consumeMany(cursor: Cursor<Char>, expression: Expression): Pair<Cursor<Char>, List<Tree<Char>>> {
        var currentCursor = cursor
        var lastSuccess = true
        val list = mutableListOf<Tree<Char>>()
        while (lastSuccess) {
            when (val result = expression.consume(currentCursor)) {
                is Success -> {
                    list.add(result.tree)
                    currentCursor = result.cursor
                }
                is Failure -> {
                    lastSuccess = false
                }
            }
        }
        return Pair(currentCursor, list)
    }

    fun consumeManyInterleave(
        cursor: Cursor<Char>,
        outer: Expression,
        inner: Expression
    ): Pair<Cursor<Char>, List<Tree<Char>>> {
        val list = mutableListOf<Tree<Char>>()
        var currentCursor = cursor
        val firstResult = outer.consume(cursor)
        var lastSuccess = firstResult.success
        if (firstResult is Success) {
            list.add(firstResult.tree)
            currentCursor = firstResult.cursor
        }
        while (lastSuccess) {
            when (val betweenResult = inner.consume(currentCursor)) {
                is Success -> {
                    when (val expressionResult = outer.consume(betweenResult.cursor)) {
                        is Success -> {
                            list.add(betweenResult.tree)
                            list.add(expressionResult.tree)
                            currentCursor = expressionResult.cursor
                        }
                        is Failure -> {
                            lastSuccess = false
                        }
                    }
                }
                is Failure -> {
                    lastSuccess = false
                }
            }
        }
        return Pair(currentCursor, list)
    }
}
