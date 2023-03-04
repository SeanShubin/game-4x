package com.seanshubin.game_4x.prototype2

data class StateMachineImpl(val state:State, override val commandScriptBuilder:CommandScriptBuilder):StateMachine {
    override fun processLine(line: Line): StateMachine {
        val (newState, newPartial) = state.processLine(commandScriptBuilder, line)
        return StateMachineImpl(newState, newPartial)
    }

    companion object {
        fun processLine(stateMachine: StateMachine, line: Line): StateMachine =
            stateMachine.processLine(line)
    }
}