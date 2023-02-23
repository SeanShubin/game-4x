package com.seanshubin.game_4x.game

object FormatUtil {
    fun formatCommand(instance:Any, vararg values:Any):String {
        val name = instance.javaClass.simpleName
        if(values.size % 2 != 0){
            throw RuntimeException("Invalid number of parameters")
        }
        val pairs = values.toList().chunked(2)
        val parametersString = pairs.joinToString(", ", transform = ::pairToString)
        return "$name($parametersString)"
    }

    private fun pairToString(pair:List<Any>):String = pairToString(pair[0], pair[1])

    private fun pairToString(key:Any, value:Any):String {
        val keyString = key.toString()
        val valueString = valueToString(value)
        return "$keyString=$valueString"
    }

    private fun valueToString(value:Any):String =
        when(value){
            is String -> """"$value""""
            is Int -> value.toString()
            is Enum<*> -> value.toString()
            else -> throw RuntimeException("Unsupported type ${value.javaClass.simpleName}")
        }
}