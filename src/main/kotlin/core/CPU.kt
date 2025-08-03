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

    fun readRAM(address: Int): Int = ram.read(address)
    fun writeRAM(address: Int, value: Int) = ram.write(address, value)

    fun readROM(address: Int): Int = rom.read(address)

    fun step(): Boolean {
        val pc = registers.p

        // If PC is outside ROM size, halt gracefully
        if (pc + 1 >= romSize()) {
            halted = true
            return true
        }

        val byte1 = readROM(pc)
        val byte2 = readROM(pc + 1)

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
