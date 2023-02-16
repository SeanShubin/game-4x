package com.seanshubin.game_4x.game

object MapUtil {
    fun <KeyType, ValueType> Map<KeyType, ValueType>.updated(
        targetKey: KeyType,
        updateFunction: (ValueType) -> ValueType
    ): Map<KeyType, ValueType> = map { (key, value) ->
        if (key == targetKey) {
            key to updateFunction(value)
        } else {
            key to value
        }
    }.toMap()
}