package com.seanshubin.game_4x.language

import com.seanshubin.game_4x.language.PBoolean.Companion.toPBoolean
import com.seanshubin.game_4x.language.PNumber.Companion.toPNumber
import com.seanshubin.game_4x.language.PString.Companion.toPString
import com.seanshubin.game_4x.language.Tree.Branch
import com.seanshubin.game_4x.language.Tree.Leaf

object AssemblersOld {
    fun assemble(tree: Tree<Char>): Any? {
        return when (tree) {
            is Branch<Char> -> assembleBranch(tree)
            is Leaf<Char> -> assembelLeaf(tree)
        }
    }

    fun assembleBranch(branch: Branch<Char>): Any? {
        val parts = branch.list.mapNotNull(::assemble)
        return when (branch.name) {
            "call" -> assembleCall(parts)
            "name" -> assembleName(parts)
            "number" -> assembleNumber(parts)
            "named" -> assembleNamed(parts)
            "unnamed" -> assembleUnnamed(parts)
            "attribute-list" -> parts
            "pair" -> assemblePair(parts)
            "string" -> assembleString(parts)
            "false" -> false.toPBoolean()
            "true" -> true.toPBoolean()
            else -> assembleGeneric(branch.list)
        }
    }

    fun assembelLeaf(leaf: Leaf<Char>): Any? =
        when (leaf.name) {
            "word-char" -> leaf.value
            "number-char" -> leaf.value
            else -> null
        }

    fun assembleCall(parts: List<Any>): Call {
        val name = parts[0] as String
        val parameterList = parts[1] as List<Value>
        return Call(name, parameterList)
    }

    fun assembleName(parts: List<Any>): String =
        parts.joinToString("")

    fun assembleGeneric(list: List<Tree<Char>>): Any? = if (list.size == 1) {
        assemble(list[0])
    } else {
        list.mapNotNull(::assemble)
    }

    fun assembleNumber(parts: List<Any>): PNumber =
        parts.joinToString("").toInt().toPNumber()

    fun assembleString(parts: List<Any>): PString =
        parts.joinToString("").toPString()

    fun assembleNamed(parts: List<Any>): Item {
        val name = parts[0] as String
        val attributes = parts[1] as List<Pair<String, Primitive>>
        val attributeList = listOf(Pair("name", name.toPString())) + attributes
        val attributeMap = attributeList.toMap()
        val item = Item(attributeMap)
        return item
    }

    fun assembleUnnamed(parts: List<Any>): Item {
        val attributeList = parts[0] as List<Pair<String, Primitive>>
        val attributeMap = attributeList.toMap()
        val item = Item(attributeMap)
        return item
    }

    fun assemblePair(parts: List<Any>): Any {
        return Pair(parts[0], parts[1])
    }
}
