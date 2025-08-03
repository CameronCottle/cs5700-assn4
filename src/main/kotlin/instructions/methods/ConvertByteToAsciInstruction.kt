package instructions.methods

import core.CPU
import instructions.Instruction

class ConvertByteToAsciiInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF
        val rY = (byte2 shr 4) and 0xF
        val value = cpu.getRegisters()[rX] and 0xFF

        val nibble = value and 0xF

        val ascii = if (nibble < 10) {
            '0'.code + nibble
        } else {
            'A'.code + (nibble - 10)
        }

        cpu.getRegisters()[rY] = ascii
    }
}
