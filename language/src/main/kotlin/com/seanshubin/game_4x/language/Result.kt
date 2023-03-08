package com.seanshubin.game_4x.language

data class Result(val success:Boolean, val cursor:Cursor<Char>, val tree:Tree<Char>) {
    companion object {
        fun success(cursor:Cursor<Char>, tree:Tree<Char>):Result = Result(success = true, cursor, tree)
        fun failure(cursor:Cursor<Char>):Result = Result(success = false, cursor, Branch("empty", emptyList()))
        fun wrap(name:String, result:Result):Result = result.copy(
            tree = Branch(name, listOf(result.tree))
        )
    }
}