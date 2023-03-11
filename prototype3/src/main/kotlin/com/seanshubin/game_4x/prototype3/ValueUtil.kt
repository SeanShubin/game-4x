package com.seanshubin.game_4x.prototype3

object ValueUtil {
    fun Any.toPrimitive(): PrimitiveValue =
        toPrimitiveOrNull() ?: throw RuntimeException(
            "Unable to covert $this of type ${this.javaClass.simpleName} to a primitive value"
        )

    fun Any.toPrimitiveOrNull(): PrimitiveValue? =
        toPrimitiveAlias() ?: toPrimitiveInt() ?: toPrimitiveBoolean() ?: toPrimitiveString()

    fun Any.toValue(): Value =
        toValueOrNull() ?: throw RuntimeException(
            "Unable to covert $this of type ${this.javaClass.simpleName} to a value"
        )

    fun Any.toValueOrNull(): Value? =
        toPrimitiveOrNull() ?: toItemOrNull()

    fun Any.toItem(): Item =
        toItemOrNull() ?: throw RuntimeException(
            "Unable to covert $this of type ${this.javaClass.simpleName} to an item"
        )

    fun Any.toItemOrNull(): Item? = when (this) {
        is Item -> this
        is Map<*, *> -> Item((this as Map<String, Any>).valuesToPrimitive())
        else -> null
    }

    fun Map<String, Any>.valuesToPrimitive(): Map<String, PrimitiveValue> = this.map { (key, value) ->
        key to value.toPrimitive()
    }.toMap()

    private fun Any.toPrimitiveAlias(): PrimitiveAlias? = when (this) {
        is String -> if (startsWith("$")) PrimitiveAlias(removePrefix("$")) else null
        else -> null
    }

    private fun Any.toPrimitiveString(): PrimitiveString? = when (this) {
        is String -> PrimitiveString(this)
        else -> null
    }

    private fun Any.toPrimitiveInt(): PrimitiveInt? = when (this) {
        is String -> {
            val intValue = toIntOrNull()
            if (intValue == null) null else PrimitiveInt(intValue)
        }
        is Int -> PrimitiveInt(this)
        else -> null
    }

    private fun Any.toPrimitiveBoolean(): PrimitiveBoolean? = when (this) {
        is String -> {
            val booleanValue = toBooleanStrictOrNull()
            if (booleanValue == null) null else PrimitiveBoolean(booleanValue)
        }
        is Boolean -> PrimitiveBoolean(this)
        else -> null
    }
}
