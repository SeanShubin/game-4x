package com.seanshubin.game_4x.language

sealed interface Result {
    val success: Boolean

    data class Success(val cursor: Cursor<Char>, val tree: Tree<Char>) : Result {
        override val success: Boolean = true
    }

    object Failure : Result {
        override val success: Boolean = false
    }

    companion object {
        fun success(cursor: Cursor<Char>, tree: Tree<Char>): Result = Success(cursor, tree)
        fun failure(): Result = Failure
        fun wrap(name: String, result: Result): Result = when (result) {
            is Success -> Success(result.cursor, Branch(name, listOf(result.tree)))
            is Failure -> Failure
        }
    }
}
