package com.seanshubin.game_4x.prototype4

data class Items(val itemMap:Map<Item, Int> = emptyMap()) {
    fun add(target:Item):Items {
        val updateQuantity = { quantity:Int -> quantity + 1}
        return this.updateQuantityOrNull(target, updateQuantity)!!
    }
    fun removeOrNull(target:Item):Items? {
        val updateQuantity = { quantity:Int -> quantity - 1}
        return this.updateQuantityOrNull(target, updateQuantity)
    }
    fun count(target:Item):Int = itemMap[target] ?: 0
    private fun updateQuantityOrNull(target:Item, update:(Int)->Int):Items? {
        val existingQuantity = itemMap[target] ?: 0
        val newQuantity = update(existingQuantity)
        return if(newQuantity < 0){
            null
        } else if(newQuantity == 0) {
            Items(itemMap.filter{ (item , _) ->  item != target  })
        } else {
            Items(itemMap + (target to newQuantity))
        }
    }
    companion object {
        fun fromItemList(itemList:List<Item>):Items =
            itemList.fold(Items()) { items, item ->
                items.add(item)
            }
    }
}
