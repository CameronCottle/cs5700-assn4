package instructions.methods

import core.CPU
import instructions.Instruction

class ReadKeyboardInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF

        print("Waiting for input: ")
        val input = readLine()?.trim()?.uppercase() ?: ""

        // Parse first 2 hex characters only
        val sanitized = input.take(2).filter { it in '0'..'9' || it in 'A'..'F' }

        // Convert hex string to int
        val value = sanitized.toIntOrNull(16) ?: 0

        // Store into register
        cpu.getRegisters()[rX] = value
    }
}
