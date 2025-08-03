package instructions.methods

import core.CPU
import instructions.Instruction

class ReadInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF
        val address = cpu.registers.a
        val mem = if (cpu.registers.m) cpu.getROM() else cpu.getRAM()
        val value = mem.read(address)

        cpu.registers[rX] = value
    }
}
