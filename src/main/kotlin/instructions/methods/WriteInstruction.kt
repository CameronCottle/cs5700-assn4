package instructions.methods

import core.CPU
import instructions.Instruction

class WriteInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    private val rX = byte1 and 0xF

    override fun perform(cpu: CPU) {
        val addr = cpu.getRegisters().a
        val value = cpu.getRegisters()[rX]
        cpu.writeRAM(addr, value)
    }
}
