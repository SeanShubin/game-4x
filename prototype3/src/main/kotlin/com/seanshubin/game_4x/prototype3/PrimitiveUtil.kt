package com.seanshubin.game_4x.prototype3

object PrimitiveUtil {
    fun String.toPrimitive():PrimitiveValue =
        toPrimitiveAlias() ?: toPrimitiveInt() ?: toPrimitiveBoolean() ?: toPrimitiveString()
    fun Map<String, String>.valuesToPrimitive():Map<String, PrimitiveValue> = this.map { (key, value) ->
        key to value.toPrimitive()
    }.toMap()
    private fun String.toPrimitiveAlias():PrimitiveAlias? =
        if(startsWith("$")) PrimitiveAlias(removePrefix("$")) else null
    private fun String.toPrimitiveString():PrimitiveString = PrimitiveString(this)
    private fun String.toPrimitiveInt():PrimitiveInt? {
        val intValue = toIntOrNull()
        return if(intValue == null) null else PrimitiveInt(intValue)
    }
    private fun String.toPrimitiveBoolean():PrimitiveBoolean? {
        val booleanValue = toBooleanStrictOrNull()
        return if(booleanValue == null) null else PrimitiveBoolean(booleanValue)
    }
}
