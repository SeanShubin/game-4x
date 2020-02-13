package com.seanshubin.game_4x.domain

import com.seanshubin.game_4x.domain.Intent.Companion.AttackCommanderNamed
import kotlin.test.Test
import kotlin.test.assertTrue

class SkirmishTest {
    @Test
    fun mutualAssuredDestruction() {
        // given
        val simpleUnit = GameUnit(attack = 1, armor = 1)
        val battlefieldBeforeSkirmish = setupBattlefield(
                1, simpleUnit,
                1, simpleUnit
        )
        // when

        val battlefieldAfterSkirmish = battlefieldBeforeSkirmish.doSkirmish()

        // then
        val aliceAfterSkirmish = battlefieldAfterSkirmish.getCommander("alice")
        val bobAfterSkirmish = battlefieldAfterSkirmish.getCommander("bob")
        assertTrue(aliceAfterSkirmish.army.isEmpty())
        assertTrue(bobAfterSkirmish.army.isEmpty())
    }

    @Test
    fun weakHitAccumulateOnArmor() {
        // given
        val simpleUnit = GameUnit(attack = 1, armor = 1)
        val strongUnit = GameUnit(attack = 1, armor = 5)
        val battlefieldBeforeSkirmish = setupBattlefield(
                5, simpleUnit,
                1, strongUnit
        )
        // when

        val battlefieldAfterSkirmish = battlefieldBeforeSkirmish.doSkirmish()

        // then
        val aliceAfterSkirmish = battlefieldAfterSkirmish.getCommander("alice")
        val bobAfterSkirmish = battlefieldAfterSkirmish.getCommander("bob")
        assertTrue(aliceAfterSkirmish.army.isEmpty())
        assertTrue(bobAfterSkirmish.army.isEmpty())
    }

    private fun setupBattlefield(aliceQuantity: Int, aliceUnit: GameUnit,
                                 bobQuantity: Int, bobUnit: GameUnit): Battlefield {
        val aliceUnits = (1..aliceQuantity).map { aliceUnit }
        val aliceArmy = Army(aliceUnits)
        val aliceIntent = AttackCommanderNamed("bob")
        val bobUnits = (1..bobQuantity).map { bobUnit }
        val bobArmy = Army(bobUnits)
        val bobIntent = AttackCommanderNamed("alice")
        val aliceBeforeSkirmish = Commander("alice", aliceArmy, aliceIntent)
        val bobBeforeSkirmish = Commander("bob", bobArmy, bobIntent)
        return Battlefield(aliceBeforeSkirmish, bobBeforeSkirmish)
    }
}