package com.seanshubin.game_4x.prototype2

data class Result(
    val success:Boolean,
    val state: Items,
    val messages: List<String>
){
    companion object {
        fun success(state:Items, messages:List<String>):Result = Result(success = true, state, messages)
        fun failure(state:Items, messages:List<String>):Result = Result(success = false, state, messages)
        fun missingParameter(state:Items, index:Int):Result = failure(state, listOf(
            "Missing parameter ${index+1}"
        ))
        fun invalidSyntax(state:Items, line: String): Result =failure(state, listOf(
            "Invalid syntax for '$line'"
        ))
        fun commandNotFound(state:Items, name: String): Result = failure(state, listOf(
            "Command '$name' not found"
        ))
    }
}
