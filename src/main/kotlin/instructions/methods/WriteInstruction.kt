package instructions.methods

import core.CPU
import instructions.Instruction

class WriteInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    private val rX = byte1 and 0xF

    override fun perform(cpu: CPU) {
        val addr = cpu.registers.a
        val value = cpu.registers[rX]

        val mem = if (cpu.registers.m == 1) cpu.getROM() else cpu.getRAM()
        mem.write(addr, value)

        println("WRITE (M=${cpu.registers.m}) ${if (cpu.registers.m == 1) "ROM" else "RAM"}[$addr] <- r$rX ($value)")
    }
}
