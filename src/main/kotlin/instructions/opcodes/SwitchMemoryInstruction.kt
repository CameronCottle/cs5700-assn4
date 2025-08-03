package instructions.opcodes

import core.CPU
import instructions.Instruction

class SwitchMemoryInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun perform(cpu: CPU) {
        cpu.getRegisters().m = if (cpu.getRegisters().m == 0) 1 else 0
    }
}

