package com.seanshubin.game_4x.prototype2

class ScriptParserImpl:ScriptParser {
    override fun parseLines(lines: List<Line>): Script {
        val builder = Builder()
        val initialBuilderState = BuilderState.Companion.ReadingHeader
        val initialStateMachine = StateMachineImpl(initialBuilderState, builder)
        val finalStateMachine = lines.fold(initialStateMachine, StateMachine::processLine)
        val finalBuilder = finalStateMachine.builder
        val script = finalBuilder.build()
        return script
    }
}