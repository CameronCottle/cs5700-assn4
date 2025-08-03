package core

import memory.Memory

class CPU(
    private val ram: Memory,
    private var rom: Memory
) {
    val registers = RegisterBank()
    private val screen = Screen.instance

    fun getScreen(): Screen = screen

    var halted = false
        private set

    fun halt() {
        halted = true
    }

    fun step(): Boolean {
        val pc = registers.p
        val mem = if (registers.m) rom else ram

        if (pc >= romSize()) return true

        val byte1 = mem.read(pc)
        val byte2 = mem.read(pc + 1)

        val instr = instructions.InstructionFactory().createInstruction(byte1, byte2)
        instr.execute(this)

        return halted
    }


    private fun romSize(): Int = (rom as memory.ROM).size()

    fun reset(newRom: Memory? = null) {
        if (newRom != null) rom = newRom
        registers.reset()
        screen.clear()
    }
}
