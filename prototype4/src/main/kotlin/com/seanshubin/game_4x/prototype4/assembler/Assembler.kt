package com.seanshubin.game_4x.prototype4.assembler

import com.seanshubin.game_4x.prototype4.language.Tree

interface Assembler {
    fun assemble(tree: Tree<Char>): Any?
}
