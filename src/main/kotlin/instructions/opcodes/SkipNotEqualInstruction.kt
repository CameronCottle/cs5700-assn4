package instructions.opcodes

import core.CPU
import instructions.Instruction

class SkipNotEqualInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    private var shouldSkip = false

    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF
        val rY = (byte2 shr 4) and 0xF

        shouldSkip = cpu.getRegisters()[rX] != cpu.getRegisters()[rY]
    }

    override fun postPerform(cpu: CPU) {
        cpu.getRegisters().p += if (shouldSkip) 4 else 2
    }
}
