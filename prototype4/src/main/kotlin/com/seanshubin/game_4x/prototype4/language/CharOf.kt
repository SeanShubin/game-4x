package com.seanshubin.game_4x.prototype4.language

import com.seanshubin.game_4x.prototype4.cursor.Cursor
import com.seanshubin.game_4x.prototype4.language.Result.Success
import com.seanshubin.game_4x.prototype4.language.Result.Failure
import com.seanshubin.game_4x.prototype4.language.Tree.Leaf
import com.seanshubin.game_4x.prototype4.language.Tree.Branch

data class CharOf(val name: String, val char: Char) : Expression {
    override fun consume(cursor: Cursor<Char>): Result =
        if (cursor.isEnd) Failure
        else if (cursor.valueIs(char)) Success(cursor.next(), Leaf(name, char))
        else Failure
}
