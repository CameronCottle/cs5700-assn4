package instructions.methods

import core.CPU
import instructions.Instruction

class SetAInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    override fun perform(cpu: CPU) {
        val address = ((byte1 and 0x0F) shl 8) or byte2
        cpu.registers.a = address
    }
}
