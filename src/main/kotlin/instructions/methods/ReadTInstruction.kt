package instructions.methods

import core.CPU
import instructions.Instruction

class ReadTInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF
        cpu.getRegisters()[rX] = cpu.getRegisters().t
    }
}
