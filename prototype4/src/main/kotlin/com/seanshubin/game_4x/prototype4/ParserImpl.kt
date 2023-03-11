package com.seanshubin.game_4x.prototype4

import com.seanshubin.game_4x.prototype4.CommandSyntax.call
import com.seanshubin.game_4x.prototype4.assembler.Assembler
import com.seanshubin.game_4x.prototype4.cursor.Cursor
import com.seanshubin.game_4x.prototype4.cursor.IndexedCursor
import com.seanshubin.game_4x.prototype4.language.Result
import com.seanshubin.game_4x.prototype4.language.Tree

class ParserImpl(private val assembler: Assembler):Parser {
    override fun parseCall(line: String): ParseResult {
        val cursor = IndexedCursor.create(line)
        return when(val result = call.consume(cursor)){
            is Result.Success -> {
                if(result.cursor.isEnd){
                    ParseResult.Success(assembleCall(result.tree))
                } else {
                    val remain = result.cursor.reify().joinToString("")
                    val parsed = cursor.between(result.cursor).joinToString("")
                    ParseResult.Failure(listOf(
                        "Unable to parse as call : '$line'",
                        "parsed this portion     : '$parsed'",
                        "but this portion remains: '$remain'"
                    ))
                }
            }
            is Result.Failure -> {
                ParseResult.Failure(listOf(
                    "Unable to parse as call: '$line'"
                ))
            }
        }
    }

    private fun assembleCall(tree: Tree<Char>):Call =
        assembler.assemble(tree) as Call
}
