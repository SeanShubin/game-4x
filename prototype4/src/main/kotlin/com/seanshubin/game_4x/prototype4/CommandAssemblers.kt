package com.seanshubin.game_4x.prototype4
import com.seanshubin.game_4x.prototype4.assembler.AssemblerUtil.delegateToNestedAssembler
import com.seanshubin.game_4x.prototype4.assembler.AssemblerUtil.listAssembler
import com.seanshubin.game_4x.prototype4.assembler.AssemblerUtil.nullAssembler
import com.seanshubin.game_4x.prototype4.assembler.AssemblerUtil.trueAssembler
import com.seanshubin.game_4x.prototype4.assembler.AssemblerUtil.falseAssembler

object CommandAssemblers {
    private val callAssembler: (parts: List<Any>) -> Call = { parts ->
        Call(
            parts[0] as String,
            parts[1].asListAny()
        )
    }

    private val nameAssembler: (parts: List<Any>) -> String = { parts ->
        parts.asListChar().joinToString("")
    }

    private val namedAssembler: (parts: List<Any>) -> Item = { parts ->
        val name = parts[0] as String
        val otherAttributes = parts[1].asListPairStringAny()
        val attributes = listOf(Pair("name", name)) + otherAttributes
        val attributeMap = attributes.toMap()
        val item = Item(attributeMap)
        item
    }

    private val numberAssembler: (parts: List<Any>) -> Int = { parts ->
        parts.asListChar().joinToString("").toInt()
    }

    private val pairAssembler: (parts: List<Any>) -> Pair<String, Any> = { parts ->
        Pair(parts[0] as String, parts[1])
    }

    private val stringAssembler: (parts: List<Any>) -> String = { parts ->
        parts.asListChar().joinToString("")
    }

    private val unNamedAssembler: (parts: List<Any>) -> Item = { parts ->
        val attributes = parts[0].asListPairStringAny()
        val attributeMap = attributes.toMap()
        val item = Item(attributeMap)
        item
    }

    private val charAssembler: (parts: List<Any>) -> Char = { parts ->
        parts.asListChar()[0]
    }

    val assemblerMap: Map<String, (List<Any>) -> Any?> = mapOf(
        "call" to callAssembler,
        "name" to nameAssembler,
        "named" to namedAssembler,
        "number" to numberAssembler,
        "pair" to pairAssembler,
        "string" to stringAssembler,
        "unnamed" to unNamedAssembler,
        "maybe-whitespace" to nullAssembler,
        "parameter-list" to listAssembler,
        "value" to delegateToNestedAssembler,
        "primitive" to delegateToNestedAssembler,
        "item" to delegateToNestedAssembler,
        "attribute-list" to listAssembler,
        "attribute" to delegateToNestedAssembler,
        "word-char" to charAssembler,
        "whitespace-char" to nullAssembler,
        "number-char" to charAssembler,
        "open-brace" to nullAssembler,
        "equal-sign" to nullAssembler,
        "close-brace" to nullAssembler,
        "boolean" to delegateToNestedAssembler,
        "false" to falseAssembler,
        "false-char" to nullAssembler,
        "true" to trueAssembler,
        "true-char" to nullAssembler
    )

    @Suppress("UNCHECKED_CAST")
    private fun List<Any>.asListChar(): List<Char> = this as List<Char>

    @Suppress("UNCHECKED_CAST")
    private fun Any.asListPairStringAny(): List<Pair<String, Any>> = this as List<Pair<String, Any>>

    @Suppress("UNCHECKED_CAST")
    private fun Any.asListAny(): List<Any> = this as List<Any>

}
