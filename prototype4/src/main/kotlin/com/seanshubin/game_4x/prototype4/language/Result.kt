package com.seanshubin.game_4x.prototype4.language

import com.seanshubin.game_4x.prototype4.cursor.Cursor
import com.seanshubin.game_4x.prototype4.language.Tree.Branch

sealed interface Result {
    val success: Boolean

    data class Success(val cursor: Cursor<Char>, val tree: Tree<Char>) : Result {
        override val success: Boolean = true
        fun wrap(name: String): Result = Success(cursor, Branch(name, listOf(tree)))
    }

    object Failure : Result {
        override val success: Boolean = false
    }

    companion object {
        fun wrap(name: String, result: Result): Result = when (result) {
            is Success -> Success(result.cursor, Branch(name, listOf(result.tree)))
            is Failure -> Failure
        }
    }
}
