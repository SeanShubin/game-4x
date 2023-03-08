package com.seanshubin.game_4x.language

object ExpressionUtil {
    fun consumeMany(cursor: Cursor<Char>, expression: Expression): Pair<Cursor<Char>, List<Tree<Char>>> {
        var currentCursor = cursor
        var lastSuccess = true
        val list = mutableListOf<Tree<Char>>()
        while (lastSuccess) {
            val result = expression.consume(currentCursor)
            if (result.success) {
                list.add(result.tree)
                currentCursor = result.cursor
            } else {
                lastSuccess = false
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
        if (firstResult.success) {
            list.add(firstResult.tree)
            currentCursor = firstResult.cursor
        }
        while (lastSuccess) {
            val betweenResult = inner.consume(currentCursor)
            if (betweenResult.success) {
                val expressionResult = outer.consume(betweenResult.cursor)
                if (expressionResult.success) {
                    list.add(betweenResult.tree)
                    list.add(expressionResult.tree)
                    currentCursor = expressionResult.cursor
                } else {
                    lastSuccess = false
                }
            } else {
                lastSuccess = false
            }
        }
        return Pair(currentCursor, list)
    }
}
