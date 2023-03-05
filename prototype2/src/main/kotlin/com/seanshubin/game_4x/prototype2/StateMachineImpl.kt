package com.seanshubin.game_4x.prototype2

data class StateMachineImpl(val builderState:BuilderState, override val builder:Builder):StateMachine {
    override fun processLine(line: Line): StateMachine {
        val (newState, newPartial) = builderState.processLine(builder, line)
        return StateMachineImpl(newState, newPartial)
    }

    companion object {
        fun processLine(stateMachine: StateMachine, line: Line): StateMachine =
            stateMachine.processLine(line)
    }
}