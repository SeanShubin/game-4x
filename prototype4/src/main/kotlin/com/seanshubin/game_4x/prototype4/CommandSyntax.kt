package com.seanshubin.game_4x.prototype4

import com.seanshubin.game_4x.prototype4.language.*

object CommandSyntax {
    val whitespaceChar = CharOf("whitespace-char", ' ')
    val whitespaceBlock = OneOrMore("whitespace-block", whitespaceChar)
    val maybeWhitespace = ZeroOrMore("maybe-whitespace", whitespaceChar)
    val wordChar = OneOfChar("word-char", ('a'..'z') + ('A'..'Z') + '-')
    val name = OneOrMore("name", wordChar)
    val numberChar = OneOfChar("number-char", ('0'..'9').toList())
    val dollar = CharOf("dollar", '$')
    val alias = SeqOf("alias", dollar, name)
    val trueValue = StringOf("true", "true-char", "true")
    val falseValue = StringOf("false", "false-char", "false")
    val booleanValue = OneOf("boolean", trueValue, falseValue)
    val stringValue = OneOrMore("string", wordChar)
    val number = OneOrMore("number", numberChar)
    val primitive = OneOf("primitive", alias, number, booleanValue, stringValue)
    val equalSign = CharOf("equal-sign", '=')
    val pair = SeqOf("pair", name, equalSign, primitive)
    val attribute = OneOf("attribute", alias, pair)
    val openBrace = CharOf("open-brace", '{')
    val attributeList = ZeroOrMoreInterleave("attribute-list", attribute, maybeWhitespace)
    val closeBrace = CharOf("close-brace", '}')
    val unnamed = SeqOf("unnamed", openBrace, attributeList, closeBrace)
    val named = SeqOf("named", openBrace, name, maybeWhitespace, attributeList, closeBrace)
    val item = OneOf("item", named, unnamed)
    val value = OneOf("value", item, primitive)
    val parameterList = ZeroOrMoreInterleave("parameter-list", value, maybeWhitespace)
    val call = SeqOf("call", name, maybeWhitespace, parameterList)
}