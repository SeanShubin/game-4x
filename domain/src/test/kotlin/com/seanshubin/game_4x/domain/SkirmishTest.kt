package com.seanshubin.game_4x.domain

import com.seanshubin.game_4x.domain.Intent.Companion.AttackCommanderNamed
import kotlin.test.Test
import kotlin.test.assertTrue

class SkirmishTest {
    @Test
    fun mutualAssuredDestruction() {
        // given
        val simpleUnit = GameUnit(attack = 1, defense = 1)
        val aliceArmy = Army(simpleUnit)
        val aliceIntent = AttackCommanderNamed("bob")
        val bobArmy = Army(simpleUnit)
        val bobIntent = AttackCommanderNamed("alice")
        val aliceBeforeSkirmish = Commander("alice", aliceArmy, aliceIntent)
        val bobBeforeSkirmish = Commander("bob", bobArmy, bobIntent)
        val battlefieldBeforeSkirmish = Battlefield(aliceBeforeSkirmish, bobBeforeSkirmish)
        // when

        val battlefieldAfterSkirmish = battlefieldBeforeSkirmish.doSkirmish()

        // then
        val aliceAfterSkirmish = battlefieldAfterSkirmish.getCommander("alice")
        val bobAfterSkirmish = battlefieldAfterSkirmish.getCommander("bob")
        assertTrue(aliceAfterSkirmish.army.isEmpty())
        assertTrue(bobAfterSkirmish.army.isEmpty())
    }

}