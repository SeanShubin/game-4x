package com.seanshubin.game_4x.game

object MapUtil {
    fun <KeyType, ValueType> Map<KeyType, ValueType>.copyRemoveKey(targetKey: KeyType): Map<KeyType, ValueType> =
        mapNotNull { (key, value) ->
            if (key == targetKey) null
            else key to value
        }.toMap()
}