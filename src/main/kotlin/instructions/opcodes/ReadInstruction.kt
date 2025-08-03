package instructions.opcodes

import core.CPU
import instructions.Instruction

class ReadInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF
        val address = cpu.getRegisters().a

        // Use CPU's encapsulated memory access
        val value = if (cpu.getRegisters().m == 1) {
            cpu.readROM(address)
        } else {
            cpu.readRAM(address)
        }

        cpu.getRegisters()[rX] = value
    }
}
