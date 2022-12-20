package com.seanshubin.game_4x.skirmish

interface Intent {
    companion object {
        data class AttackCommanderNamed(val defenderName: String) : Intent
    }
}
