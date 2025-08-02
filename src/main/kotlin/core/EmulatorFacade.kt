package core

import memory.RAM
import memory.ROM

class EmulatorFacade {

    private val ram = RAM()
    private var rom = ROM(IntArray(4096))
    private val cpu = CPU(ram, rom)
    private val loader = ProgramLoader()
    private val screen = Screen.instance  // Screen lives in Facade now

    fun loadProgram(path: String) {
        val program = loader.loadOutFile(path)
        println("Loaded program with ${program.size} nibbles")

        rom = ROM(program)
        cpu.reset(rom)
        screen.clear()
    }

    fun step(): Boolean {
        val halted = cpu.step()

        // Only draw if a character was actually drawn
        if (cpu.screenDirty) {
            drawScreen()
            cpu.screenDirty = false
        }

        return halted
    }

    fun run(maxSteps: Int = 1000) {
        var steps = 0
        while (steps < maxSteps) {
            if (step()) return
            steps++
        }
        println("Max steps reached ($maxSteps) without halt.")
    }

    private fun drawScreen() {
        for (row in screen.snapshot()) {
            println(row.concatToString())
        }
        println("========")
    }

    fun reset() {
        cpu.registers.reset()
        for (i in 0 until 4096) ram.write(i, 0)
        screen.clear()
    }
}
