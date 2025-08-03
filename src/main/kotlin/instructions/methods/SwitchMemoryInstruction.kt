package instructions.methods

import core.CPU
import instructions.Instruction

class SwitchMemoryInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun perform(cpu: CPU) {
        cpu.registers.m = if (cpu.registers.m == 0) 1 else 0
    }
}

