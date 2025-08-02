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
        val byte1 = mem.read(pc)
        val byte2 = mem.read(pc + 1)

        val instr = instructions.InstructionFactory().createInstruction(byte1, byte2)
        instr.execute(this)

        // ✅ Simple HALT check
        return byte1 == 0xF0 && byte2 == 0x00
    }

    fun reset(newRom: Memory? = null) {
        if (newRom != null) rom = newRom
        registers.reset()
        screen.clear()  // ✅ Also clear the screen on reset
    }
}
