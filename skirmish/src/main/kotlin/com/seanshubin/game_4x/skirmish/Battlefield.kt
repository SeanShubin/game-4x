package com.seanshubin.game_4x.skirmish

import com.seanshubin.game_4x.skirmish.Intent.Companion.AttackCommanderNamed

data class Battlefield(val commanders: List<Commander>) {
    constructor(vararg commanders: Commander) : this(commanders.toList())

    fun doSkirmish(): Battlefield =
        commanders.fold(this, ::doSkirmishWithCommander)

    fun getCommander(name: String): Commander =
        commanders.find { it.name == name }
            ?: throw RuntimeException("Commander named '$name' not found")

    private fun doSkirmishWithCommander(battlefield: Battlefield, commander: Commander): Battlefield =
        when (val intent = commander.intent) {
            is AttackCommanderNamed -> attackCommander(battlefield, commander, intent.defenderName)
            else -> throw UnsupportedOperationException("Unknown intent '$intent'")
        }

    private fun attackCommander(battlefield: Battlefield, initiator: Commander, defenderName: String): Battlefield {
        val defender = battlefield.getCommander(defenderName)
        val volley = initiator.fireVolley()
        val defenderAfterHit = defender.receiveVolley(volley)
        return battlefield.updateCommander(defenderAfterHit)

    }

    private fun updateCommander(commander: Commander): Battlefield {
        val newCommanders = commanders.map {
            if (it.name == commander.name) {
                commander
            } else {
                it
            }
        }
        return copy(commanders = newCommanders)
    }
}
