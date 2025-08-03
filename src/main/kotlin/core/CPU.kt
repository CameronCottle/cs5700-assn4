package core

import memory.Memory
import memory.ROM

class CPU(
    private val ram: Memory,
    private var rom: Memory
) {
    val registers = RegisterBank()
    private val screen = Screen.instance
    fun getRAM(): Memory = ram
    fun getROM(): Memory = rom
    fun getScreen() = screen

    var halted = false
        private set

    /** Facade methods for memory access */
    fun readMem(address: Int): Int {
        val mem = if (registers.m) rom else ram
        return mem.read(address)
    }

    fun writeMem(address: Int, value: Int) {
        // Only RAM is writable
        ram.write(address, value)
    }

    /** Single-step CPU execution */
    fun step(): Boolean {
        val pc = registers.p

        // Stop if PC is outside ROM (safety)
        if (pc >= romSize()) return true

        val byte1 = readMem(pc)
        val byte2 = readMem(pc + 1)

        val instr = instructions.InstructionFactory().createInstruction(byte1, byte2)
        instr.execute(this)

        return halted
    }

    fun halt() {
        halted = true
    }

    private fun romSize(): Int = (rom as ROM).size()

    /** Reset CPU and optionally load a new ROM */
    fun reset(newRom: Memory? = null) {
        if (newRom != null) rom = newRom
        registers.reset()
        screen.clear()
        halted = false
    }
}
