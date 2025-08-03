package instructions

import core.CPU

abstract class Instruction(val byte1: Int, val byte2: Int) {
    val opcode: Int get() = (byte1 shr 4) and 0xF

    // Template method
    fun execute(cpu: CPU) {
        preExecute(cpu)
        perform(cpu)
        postExecute(cpu)
    }

    protected open fun preExecute(cpu: CPU) {}
    protected abstract fun perform(cpu: CPU)
    protected open fun postExecute(cpu: CPU) {
        cpu.registers.p += 2
    }
}
