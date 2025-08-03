package core

import memory.Memory
import memory.ROM
import instructions.Instruction
import instructions.InstructionFactory

class CPU(
    private val ram: Memory,
    private var rom: Memory
) {
    private val registers = RegisterBank()
    private val screen = Screen.instance
    private val timer = Timer(registers)

    var halted = false
        private set

    /** Memory access methods (encapsulation) */
    fun readRAM(address: Int): Int = ram.read(address)
    fun writeRAM(address: Int, value: Int) = ram.write(address, value)
    fun readROM(address: Int): Int = rom.read(address)
    fun getRegisters(): RegisterBank = registers


    /** Screen access */
    fun getScreen(): Screen = screen

    /** Timer control */
    fun startTimer() = timer.start()
    fun stopTimer() = timer.stop()

    /** CPU step (Template Method: fetch → decode → execute) */
    fun step(): Boolean {
        if (isPCOutOfRange()) return haltAndReturn()

        val instruction = fetchAndDecode()
        instruction.execute(this)  // Delegates to Instruction template method

        return halted
    }

    /** Halt CPU */
    fun halt() {
        halted = true
    }

    /** Reset CPU and optionally load a new ROM */
    fun reset(newRom: Memory? = null) {
        if (newRom != null) rom = newRom
        registers.reset()
        screen.clear()
        halted = false
    }

    /** --- Private helpers --- */

    private fun isPCOutOfRange(): Boolean = registers.p + 1 >= romSize()

    private fun haltAndReturn(): Boolean {
        halted = true
        return true
    }

    private fun romSize(): Int = (rom as ROM).size()

    private fun fetchAndDecode(): Instruction {
        val pc = registers.p
        val byte1 = readROM(pc)
        val byte2 = readROM(pc + 1)
        return InstructionFactory().createInstruction(byte1, byte2)
    }
}
