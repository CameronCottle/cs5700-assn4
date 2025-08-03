package instructions.methods

import core.CPU
import instructions.Instruction

class JumpInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    override fun perform(cpu: CPU) {
        // Extract 12-bit address from byte1 + byte2
        val address = ((byte1 and 0xF) shl 8) or byte2

        // Ensure address is even (divisible by 2)
        if (address % 2 != 0) {
            throw IllegalStateException("Jump to odd address $address is invalid")
        }

        cpu.registers.p = address
    }

    override fun postExecute(cpu: CPU) {
        // Override default increment — Jump does not auto-increment
    }
}
