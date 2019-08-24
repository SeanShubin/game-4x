package com.seanshubin.game_4x.domain

data class Battlefield(val commanders: List<Commander>) {
    constructor(vararg commanders: Commander) : this(commanders.toList())

    fun doSkirmish(): Battlefield =
            commanders.fold(this, ::doSkirmishWithCommander)

    fun getCommander(name: String): Commander =
            commanders.find { it.name == name }
                    ?: throw RuntimeException("Commander named '$name' not found")

    private fun doSkirmishWithCommander(battlefield: Battlefield, commander: Commander): Battlefield =
            commander.intent.apply(commander, battlefield)

    fun updateCommander(commander: Commander): Battlefield {
        val newCommanders = commanders.map {
            if(it.name == commander.name){
                commander
            } else {
                it
            }
        }
        return copy(commanders = newCommanders)
    }
}
