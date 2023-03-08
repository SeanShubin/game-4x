package com.seanshubin.game_4x.language

data class CharOf(val name: String, val char: Char) : Expression {
    override fun consume(cursor: Cursor<Char>): Result =
        if (cursor.isEnd) Result.failure()
        else if (cursor.valueIs(char)) Result.success(cursor.next(), Leaf(name, char))
        else Result.failure()
}
