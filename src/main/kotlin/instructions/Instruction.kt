package instructions

import core.CPU

abstract class Instruction(val byte1: Int, val byte2: Int) {
    fun execute(cpu: CPU) {
        perform(cpu)
        postPerform(cpu)
    }

    protected abstract fun perform(cpu: CPU)
    protected open fun postPerform(cpu: CPU) {
        cpu.getRegisters().p += 2
    }
}
