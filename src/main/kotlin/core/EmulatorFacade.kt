package core

import memory.RAM
import memory.ROM

class EmulatorFacade {

    private val ram = RAM()
    private var rom = ROM(IntArray(4096))
    private val cpu = CPU(ram, rom)
    private val loader = ProgramLoader()

    /** Load program from .out file into ROM */
    fun loadProgram(path: String) {
        val program = loader.loadOutFile(path)
        println("Loaded program with ${program.size} nibbles")

        rom = ROM(program)
        cpu.reset(rom)
    }

    /** Step the CPU once */
    fun step(): Boolean {
        val halted = cpu.step()
        return halted
    }

    /** Run until halt or max steps */
    fun run(maxSteps: Int = 1000) {
        repeat(maxSteps) {
            if (step()) return
        }
        println("Max steps reached ($maxSteps) without halt.")
    }

    /** Reset everything */
    fun reset() {
        cpu.reset()
        for (i in 0 until 4096) ram.write(i, 0)
    }
}
