package instructions

import instructions.methods.AddInstruction
import instructions.methods.StoreInstruction
import instructions.methods.HaltInstruction
// import instructions.methods.DrawInstruction

class InstructionFactory {
    fun createInstruction(byte1: Int, byte2: Int): Instruction {
        val opcode = (byte1 shr 4) and 0xF
        return when (opcode) {
            0x0 -> StoreInstruction(byte1, byte2)
            0x1 -> AddInstruction(byte1, byte2)
            0xF -> HaltInstruction(byte1, byte2)
            else -> throw IllegalArgumentException("Invalid opcode: $opcode")
        }
    }
}
