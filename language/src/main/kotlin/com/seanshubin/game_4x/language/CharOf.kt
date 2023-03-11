package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.Result.Failure
import com.seanshubin.game_4x.language.Result.Success
import com.seanshubin.game_4x.language.Tree.Leaf

data class CharOf(val name: String, val char: Char) : Expression {
    override fun consume(cursor: Cursor<Char>): Result =
        if (cursor.isEnd) Failure
        else if (cursor.valueIs(char)) Success(cursor.next(), Leaf(name, char))
        else Failure
}
