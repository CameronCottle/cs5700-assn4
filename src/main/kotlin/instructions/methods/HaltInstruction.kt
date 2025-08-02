package instructions.methods

import core.CPU
import instructions.Instruction

class HaltInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun execute(cpu: CPU) {
        // HALT does nothing; CPU.step() will handle halting
    }
}
