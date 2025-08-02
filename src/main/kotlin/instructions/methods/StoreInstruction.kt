package instructions.methods

import core.CPU
import core.NibbleParser
import instructions.Instruction

class StoreInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun execute(cpu: CPU) {
        val rX = NibbleParser.lowNibble(byte1) and 0x7 // 0–7 only
        cpu.registers.set(rX, byte2)
        println("STORE: r$rX <- 0x${byte2.toString(16).uppercase()} (${byte2.toChar()})")
        cpu.registers.p += 2
    }

}