package com.seanshubin.game_4x.prototype4.assembler

import com.seanshubin.game_4x.prototype4.language.Tree
import com.seanshubin.game_4x.prototype4.language.Tree.Branch
import com.seanshubin.game_4x.prototype4.language.Tree.Leaf

class AssemblerImpl(private val assemblerMap: Map<String, AssembleFunction>) : Assembler {
    override fun assemble(tree: Tree<Char>): Any? {
        return when (tree) {
            is Branch<Char> -> {
                val assembler = assemblerMap[tree.name]
                if (assembler == null) {
                    throw RuntimeException("No assembler defined for '${tree.name}'")
                } else {
                    assembleBranch(assembler, tree.list)
                }
            }
            is Leaf<Char> -> {
                val assemble = assemblerMap[tree.name]
                if (assemble == null) {
                    throw RuntimeException("No assembler defined for '${tree.name}'")
                } else {
                    assemble(listOf(tree.value))
                }
            }
        }
    }

    private fun assembleBranch(assemble: AssembleFunction, list: List<Tree<Char>>): Any? {
        val parts = list.mapNotNull(::assembleTreeOrNull)
        return assemble(parts)
    }

    private fun assembleTreeOrNull(tree: Tree<Char>): Any? = assemble(tree)
}
