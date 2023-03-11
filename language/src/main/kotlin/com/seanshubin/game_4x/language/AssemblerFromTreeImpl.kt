package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.Tree.Branch
import com.seanshubin.game_4x.language.Tree.Leaf

class AssemblerFromTreeImpl(val assemblerMap: Map<String, Assembler>) : AssemblerFromTree {
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
                val assembler = assemblerMap[tree.name]
                if (assembler == null) {
                    throw RuntimeException("No assembler defined for '${tree.name}'")
                } else {
                    assembler.assemble(listOf(tree.value))
                }
            }
        }
    }

    private fun assembleBranch(assembler: Assembler, list: List<Tree<Char>>): Any? {
        val parts = list.mapNotNull(::assembleTreeOrNull)
        return assembler.assemble(parts)
    }

    private fun assembleTreeOrNull(tree: Tree<Char>): Any? = assemble(tree)
}