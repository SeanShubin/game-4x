package com.seanshubin.game_4x.language

class OneOfChar(val name:String, val chars: List<Char>) : Expression {
    override fun consume(cursor: Cursor<Char>): Result =
        if (cursor.isEnd) Result.failure()
        else if (chars.contains(cursor.value)) Result.success(cursor.next(), Leaf(name, cursor.value))
        else Result.failure()
}
