package instructions

import core.NibbleParser
import instructions.methods.StoreInstruction

class InstructionFactory {
    fun createInstruction(byte1: Int, byte2: Int): Instruction {
        return when ((byte1 shr 4) and 0xF) {
            0x0 -> StoreInstruction(byte1, byte2)
            0x1 -> AddInstruction(byte1, byte2)
            0x2 -> SubInstruction(byte1, byte2)
            0x3 -> ReadInstruction(byte1, byte2)
            0x4 -> WriteInstruction(byte1, byte2)
            0x5 -> JumpInstruction(byte1, byte2)
            0x6 -> ReadKeyboardInstruction(byte1, byte2)
            0x7 -> SwitchMemoryInstruction(byte1, byte2)
            0x8 -> SkipEqualInstruction(byte1, byte2)
            0x9 -> SkipNotEqualInstruction(byte1, byte2)
            0xA -> SetAInstruction(byte1, byte2)
            0xB -> SetTInstruction(byte1, byte2)
            0xC -> ReadTInstruction(byte1, byte2)
            0xD -> ConvertToBase10Instruction(byte1, byte2)
            0xE -> ConvertByteToAsciiInstruction(byte1, byte2)
            0xF -> DrawInstruction(byte1, byte2)
            else -> throw IllegalArgumentException("Invalid opcode: ${byte1 shr 4}")
        }
    }
}

