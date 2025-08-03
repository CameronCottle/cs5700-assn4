package instructions.methods

import core.CPU
import core.Screen
import instructions.Instruction

class DrawInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    override fun perform(cpu: CPU) {
        val regIndex = byte1 and 0x0F
        val row = (byte2 shr 4) and 0x0F
        val col = byte2 and 0x0F

        val charValue = cpu.registers.get(regIndex)
        if (charValue > 0x7F) {
            throw IllegalStateException("Cannot draw non-ASCII character $charValue")
        }

        Screen.instance.write(col, row, charValue.toChar())
    }
}
