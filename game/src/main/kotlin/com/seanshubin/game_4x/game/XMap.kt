package com.seanshubin.game_4x.game

data class XMap<KeyType, ValueType>(val map: LinkedHashMap<KeyType, ValueType>) {
    fun getValue(key: KeyType): ValueType = map.getValue(key)
    fun keys():List<KeyType> = map.map { it.key }
    fun values(): List<ValueType>  = map.map { it.value }
    fun <NewValueType> mapValues(f: (ValueType) -> NewValueType): XMap<KeyType, NewValueType> =
        XMap(map.mapValues { (_, value) ->
            f(value)
        } as LinkedHashMap<KeyType, NewValueType>)
    operator fun plus(entry: Pair<KeyType, ValueType>): XMap<KeyType, ValueType> {
        val clone = cloneBacking()
        clone[entry.first] = entry.second
        return XMap(clone)
    }
    fun updated(key:KeyType, updateFunction:(ValueType)->ValueType):XMap<KeyType, ValueType>{
        val newMap = cloneBacking()
        val oldValue = newMap.getValue(key)
        val newValue = updateFunction(oldValue)
        newMap[key] = newValue
        return XMap(newMap)
    }
    private fun cloneBacking(): LinkedHashMap<KeyType, ValueType> =
        @Suppress("UNCHECKED_CAST")
        map.clone() as LinkedHashMap<KeyType, ValueType>

    companion object {
        fun <K,V> emptyMap():XMap<K,V> = XMap(LinkedHashMap())
    }
}
