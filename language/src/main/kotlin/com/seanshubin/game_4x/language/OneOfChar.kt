package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.Result.Failure
import com.seanshubin.game_4x.language.Result.Success
import com.seanshubin.game_4x.language.Tree.Leaf

class OneOfChar(val name: String, val chars: List<Char>) : Expression {
    override fun consume(cursor: Cursor<Char>): Result =
        if (cursor.isEnd) Failure
        else if (chars.contains(cursor.value)) Success(cursor.next(), Leaf(name, cursor.value))
        else Failure
}
