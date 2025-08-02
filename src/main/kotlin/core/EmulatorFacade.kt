package core

import memory.RAM
import memory.ROM

/**
 * Facade for the D5700 CPU system.
 * Orchestrates loading, execution, and debug without exposing internals.
 */
class EmulatorFacade {

    private val ram = RAM()
    private var rom = ROM(IntArray(4096))  // fresh ROM, will be replaced on load
    private val cpu = CPU(ram, rom)
    private val loader = ProgramLoader()

    /**
     * Load a program file into ROM and reset the CPU.
     */
    fun loadProgram(path: String) {
        val program = loader.loadOutFile(path)
        println("Loaded program with ${program.size} nibbles")

        rom = ROM(program)
        cpu.reset(rom)

        dumpROM(16)  // Optional: show first bytes for verification
    }

    /**
     * Execute a single CPU instruction.
     * Returns true if the CPU signals a halt.
     */
    fun step(): Boolean {
        val halted = cpu.step()
        printScreen() // Optional: visualize after each step
        return halted
    }

    /**
     * Run until halt or maxSteps.
     */
    fun run(maxSteps: Int = 1000) {
        var steps = 0
        while (steps < maxSteps) {
            val halted = step()
            steps++
            if (halted) {
                println("Execution finished after $steps steps.")
                return
            }
        }
        println("Max steps reached ($maxSteps) without halt.")
    }

    /**
     * Reset RAM and CPU registers.
     */
    fun reset() {
        cpu.registers.reset()
        for (i in 0 until 4096) ram.write(i, 0)
    }

    /**
     * Debug: Dump the first N nibbles of ROM.
     */
    fun dumpROM(n: Int = 16) {
        println("First $n nibbles of ROM:")
        for (i in 0 until n) {
            val value = rom.read(i)
            print(String.format("%X ", value))
        }
        println()
    }

    private fun printScreen() {
        val screen = Screen.instance.snapshot()
        for (row in screen) {
            println(row.concatToString())
        }
        println("========")
    }


}
