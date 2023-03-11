package com.seanshubin.game_4x.prototype4.language

import com.seanshubin.game_4x.prototype4.language.Result.Success
import com.seanshubin.game_4x.prototype4.language.Result.Failure
import com.seanshubin.game_4x.prototype4.language.Tree.Leaf
import com.seanshubin.game_4x.prototype4.language.Tree.Branch
import com.seanshubin.game_4x.prototype4.cursor.Cursor

data class StringOf(val name: String, val charName:String, val value: String) : Expression {
    override fun consume(cursor: Cursor<Char>): Result {
        var index = 0
        var currentCursor = cursor
        val charList = mutableListOf<Leaf<Char>>()
        while(index < value.length){
            if(currentCursor.isEnd) return Failure
            if(value[index] == currentCursor.value){
                charList.add(Leaf(charName, currentCursor.value))
                currentCursor = currentCursor.next()
                index++
            } else {
                return Failure
            }
        }
        return Success(currentCursor, Branch(name, charList))
    }
}
