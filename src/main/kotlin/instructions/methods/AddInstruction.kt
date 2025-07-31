package instructions.methods

import core.CPU
import instructions.Instruction

class AddInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun execute(cpu: CPU) {
        val rX = (byte1 and 0xF)
        val rY = (byte2 shr 4) and 0xF
        val rZ = byte2 and 0xF
        val sum = (cpu.registers.get(rX) + cpu.registers.get(rY)) and 0xFF
        cpu.registers.set(rZ, sum)
        cpu.registers.p += 2
    }
}
