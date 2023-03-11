package com.seanshubin.game_4x.prototype4

class LineSourceImpl(val delegate:()->String?):LineSource {
    private var pendingLines:List<String> = emptyList()
    override fun putLines(lines: List<String>) {
        pendingLines = pendingLines + lines
    }
    override fun readLine(): String? =
        if(pendingLines.isEmpty()){
            delegate()
        } else {
            consumePending()
        }
    private fun consumePending():String {
        val line = pendingLines[0]
        pendingLines = pendingLines.drop(1)
        return line
    }
}
