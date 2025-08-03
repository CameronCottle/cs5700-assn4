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
    private val screen = Screen
    private val timer = Timer(registers)

    var halted = false
        private set

    private fun isPCOutOfRange(): Boolean = registers.p + 1 >= romSize()

    private fun romSize(): Int = (rom as ROM).size()

    private fun fetchAndDecode(): Instruction {
        val pc = registers.p
        val byte1 = readROM(pc)
        val byte2 = readROM(pc + 1)
        return InstructionFactory().createInstruction(byte1, byte2)
    }

    fun readRAM(address: Int): Int = ram.read(address)
    fun writeRAM(address: Int, value: Int) = ram.write(address, value)
    fun readROM(address: Int): Int = rom.read(address)
    fun getRegisters(): RegisterBank = registers
    fun getScreen(): Screen = screen

    fun startTimer() = timer.start()
    fun stopTimer() = timer.stop()

    fun step(): Boolean {
        if (isPCOutOfRange()) return halt()

        val instruction = fetchAndDecode()
        instruction.execute(this)

        return halted
    }

    fun halt(): Boolean {
        halted = true
        return true
    }

    fun reset(newRom: Memory? = null) {
        if (newRom != null) rom = newRom
        registers.reset()
        screen.clear()
        halted = false
    }
}
