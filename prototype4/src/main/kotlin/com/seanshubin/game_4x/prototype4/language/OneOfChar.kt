package com.seanshubin.game_4x.prototype4.language

import com.seanshubin.game_4x.prototype4.cursor.Cursor
import com.seanshubin.game_4x.prototype4.language.Result.Failure
import com.seanshubin.game_4x.prototype4.language.Result.Success
import com.seanshubin.game_4x.prototype4.language.Tree.Leaf

class OneOfChar(val name: String, val chars: List<Char>) : Expression {
    override fun consume(cursor: Cursor<Char>): Result =
        if (cursor.isEnd) Failure
        else if (chars.contains(cursor.value)) Success(cursor.next(), Leaf(name, cursor.value))
        else Failure
}
