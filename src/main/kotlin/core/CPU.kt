package core

import memory.Memory

class CPU(
    private val ram: Memory,
    private var rom: Memory
) {
    val registers = RegisterBank()

    // ✅ Use singleton Screen
    private val screen = Screen.instance

    fun getScreen(): Screen = screen

    fun step(): Boolean {
        val pc = registers.p
        val mem = if (registers.m) rom else ram

        // stop if we are past the last instruction
        if (pc >= (romSize())) {
            return true
        }

        val byte1 = mem.read(pc)
        val byte2 = mem.read(pc + 1)

        val instr = instructions.InstructionFactory().createInstruction(byte1, byte2)
        instr.execute(this)

        // HALT on 00 00
        return byte1 == 0x00 && byte2 == 0x00
    }

    // Helper to get ROM size
    private fun romSize(): Int {
        return (rom as memory.ROM).size() // add size() in ROM
    }



    fun reset(newRom: Memory? = null) {
        if (newRom != null) rom = newRom
        registers.reset()
        screen.clear()
    }
}
