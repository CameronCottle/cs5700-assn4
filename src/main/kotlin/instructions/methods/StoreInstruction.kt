package instructions.methods

import core.CPU
import instructions.Instruction


class StoreInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun execute(cpu: CPU) {
        val regIndex = byte1 and 0xF
        cpu.registers.set(regIndex, byte2)
        cpu.registers.p += 2 // advance PC
    }
}
