package instructions.opcodes

import core.CPU
import instructions.Instruction

class DrawInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {

    override fun perform(cpu: CPU) {
        val rX = byte1 and 0xF   // char register
        val rY = (byte2 shr 4) and 0xF  // row
        val rZ = byte2 and 0xF          // col

        val value = cpu.getRegisters()[rX] and 0xFF
        if (value > 0x7F) {
            throw IllegalStateException(
                "Program terminated: DRAW invalid value $value in r$rX"
            )
        }

        cpu.getScreen().write(rZ, rY, value)
        cpu.getScreen().render()
    }
}
