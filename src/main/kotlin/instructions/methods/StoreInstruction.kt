package instructions.methods

import core.CPU
import core.NibbleParser
import instructions.Instruction

class StoreInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    override fun perform(cpu: CPU) {
        val rX = NibbleParser.lowNibble(byte1) and 0x7
        cpu.registers.set(rX, byte2)
        // No need to manually increment PC here; default `postExecute` handles it
    }
}
