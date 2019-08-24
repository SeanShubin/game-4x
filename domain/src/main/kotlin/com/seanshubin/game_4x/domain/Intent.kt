package com.seanshubin.game_4x.domain

interface Intent {
    companion object {
        data class AttackCommanderNamed(val defenderName: String) : Intent
    }
}
