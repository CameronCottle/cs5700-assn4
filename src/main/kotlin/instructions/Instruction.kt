package instructions

import core.CPU

abstract class Instruction(val byte1: Int, val byte2: Int) {
    abstract fun execute(cpu: CPU)

    val opcode: Int get() = (byte1 shr 4) and 0xF
}
