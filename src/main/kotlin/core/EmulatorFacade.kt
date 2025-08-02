package core

import memory.RAM
import memory.ROM

class EmulatorFacade {

    private val ram = RAM()
    private var rom = ROM(IntArray(4096))  // fresh ROM, will be replaced on load
    private val cpu = CPU(ram, rom)
    private val loader = ProgramLoader()

    fun loadProgram(path: String) {
        val program = loader.loadOutFile(path)
        println("Loaded program with ${program.size} nibbles")

        rom = ROM(program)
        cpu.reset(rom)
    }

    fun step(): Boolean {
        val halted = cpu.step()
        return halted
    }

    fun run(maxSteps: Int = 1000) {
        var steps = 0
        while (steps < maxSteps) {
            val halted = step()
            steps++
            if (halted) {
                return
            }
        }
        println("Max steps reached ($maxSteps) without halt.")
    }

    fun reset() {
        cpu.registers.reset()
        for (i in 0 until 4096) ram.write(i, 0)
    }

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
