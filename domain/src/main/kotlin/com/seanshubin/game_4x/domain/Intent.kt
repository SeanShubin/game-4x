package com.seanshubin.game_4x.domain

interface Intent {
    fun apply(initiator:Commander, battlefield: Battlefield):Battlefield
    companion object{
        data class AttackCommanderNamed(val defenderName:String):Intent {
            override fun apply(initiator:Commander, battlefield: Battlefield):Battlefield {
                val defender = battlefield.getCommander(defenderName)
                val volley= initiator.fireVolley()
                val defenderAfterHit = defender.receiveVolley(volley)
                return battlefield.updateCommander(defenderAfterHit)
            }
        }
    }
}
