package instructions.methods

import core.CPU
import instructions.Instruction

class DrawInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
    override fun execute(cpu: CPU) {
        val regIndex = byte1 and 0x0F   // which register holds the ASCII
        val row = (byte2 shr 4) and 0x0F
        val col = byte2 and 0x0F

        val charValue = cpu.registers.get(regIndex)

        if (charValue > 0x7F) {
            throw IllegalStateException("Cannot draw non-ASCII character $charValue")
        }

        val screen = cpu.getScreen()
        screen.write(col, row, charValue.toChar()) // (x, y)
        screen.draw()

        cpu.registers.p += 2
    }
}
