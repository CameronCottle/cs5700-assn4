package instructions.methods

import core.CPU
import instructions.Instruction

class DrawInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF       // char register
        val rY = (byte2 shr 4) and 0xF  // row
        val rZ = byte2 and 0xF         // column

        val charValue = cpu.registers[rX]

        // validate per datasheet
        require(charValue <= 0x7F) { "DRAW: ASCII value too high ($charValue)" }
        require(rY in 0..7 && rZ in 0..7) { "DRAW: Invalid coordinates row=$rY col=$rZ" }

        // write to screen's internal RAM
        cpu.getScreen().write(rZ, rY, charValue)

        // render immediately (since this is the only time the screen changes)
        cpu.getScreen().render()
    }
}
