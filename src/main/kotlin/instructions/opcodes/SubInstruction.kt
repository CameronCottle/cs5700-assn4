package instructions.opcodes

import core.CPU
import instructions.Instruction

class SubInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF
        val rY = (byte2 shr 4) and 0xF
        val rZ = byte2 and 0xF

        // Perform subtraction and wrap to 0-255 (like unsigned 8-bit)
        val diff = (cpu.getRegisters()[rX] - cpu.getRegisters()[rY]) and 0xFF
        cpu.getRegisters()[rZ] = diff
    }
}
