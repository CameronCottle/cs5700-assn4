package instructions.methods

import core.CPU
import instructions.Instruction

class StoreInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun perform(cpu: CPU) {
        val rX = byte1 and 0x07  // lowest 3 bits for register
        cpu.getRegisters()[rX] = byte2
    }
}

