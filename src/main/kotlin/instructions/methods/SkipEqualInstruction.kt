package instructions.methods

import core.CPU
import instructions.Instruction

class SkipEqualInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    private var shouldSkip = false

    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF
        val rY = (byte2 shr 4) and 0xF

        // Determine if we should skip the next instruction
        shouldSkip = cpu.registers[rX] == cpu.registers[rY]
    }

    override fun postExecute(cpu: CPU) {
        cpu.registers.p += if (shouldSkip) 4 else 2
    }
}
