package instructions.methods

import core.CPU
import instructions.Instruction

class SetTInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun perform(cpu: CPU) {
        // Combine the lower 4 bits of byte1 and upper 4 bits of byte2
        val value = ((byte1 and 0xF) shl 4) or (byte2 shr 4)
        cpu.getRegisters().t = value and 0xFF  // Ensure it's a byte
    }
}
