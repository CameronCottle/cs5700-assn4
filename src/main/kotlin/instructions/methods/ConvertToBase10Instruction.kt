package instructions.methods

import core.CPU
import instructions.Instruction

class ConvertToBase10Instruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF
        val value = cpu.getRegisters()[rX] and 0xFF

        val hundreds = value / 100
        val tens = (value / 10) % 10
        val ones = value % 10

        val baseAddr = cpu.getRegisters().a
        cpu.writeRAM(baseAddr, hundreds)
        cpu.writeRAM(baseAddr + 1, tens)
        cpu.writeRAM(baseAddr + 2, ones)
    }
}
