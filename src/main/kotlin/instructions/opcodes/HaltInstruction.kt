package instructions.opcodes

import core.CPU
import instructions.Instruction

class HaltInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun perform(cpu: CPU) {
        cpu.halt()  // Sets the CPU halted flag
    }
    override fun postPerform(cpu: CPU) {
        // do nothing
    }
}
