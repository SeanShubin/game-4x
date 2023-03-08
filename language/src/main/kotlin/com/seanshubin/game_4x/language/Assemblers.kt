package com.seanshubin.game_4x.language

object Assemblers {
    val assemblerMap = mapOf(
        "call" to CallAssembler,
        "name" to NameAssembler,
        "named" to NamedAssembler,
        "number" to NumberAssembler,
        "pair" to PairAssembler,
        "string" to StringAssembler,
        "unnamed" to UnnamedAssembler,
        "maybe-whitespace" to NullAssembler,
        "parameter-list" to ListAssembler,
        "value" to DelegateToNestedAssembler,
        "primitive" to DelegateToNestedAssembler,
        "item" to DelegateToNestedAssembler,
        "attribute-list" to ListAssembler,
        "attribute" to DelegateToNestedAssembler,
        "word-char" to CharAssembler,
        "whitespace-char" to NullAssembler,
        "number-char" to CharAssembler,
        "open-brace" to NullAssembler,
        "equal-sign" to NullAssembler,
        "close-brace" to NullAssembler,
        "boolean" to DelegateToNestedAssembler,
        "false" to FalseAssembler,
        "false-char" to NullAssembler,
        "true" to TrueAssembler,
        "true-char" to NullAssembler
    )
}
