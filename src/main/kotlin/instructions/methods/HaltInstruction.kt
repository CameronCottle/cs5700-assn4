package instructions.methods

import core.CPU
import instructions.Instruction

class HaltInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun perform(cpu: CPU) {
        cpu.halt()  // Sets the CPU halted flag
    }
    override fun postExecute(cpu: CPU) {
        // do nothing
    }
}
