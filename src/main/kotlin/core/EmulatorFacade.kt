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
        rom = ROM(program)
        cpu.reset(rom)
    }

    /** Step the CPU once */
    fun step(): Boolean = cpu.step()

    /** Run until halt */
    fun run(maxSteps: Int = Int.MAX_VALUE) {
        cpu.startTimer()  // Start 60Hz timer

        val targetHz = 500        // CPU frequency ~500Hz
        val sleepTime = 1000L / targetHz

        var steps = 0
        while (!cpu.halted && steps < maxSteps) {
            step()
            Thread.sleep(sleepTime)  // Pace CPU
            steps++
        }

        cpu.stopTimer() // Stop timer when done
    }

    /** Reset everything */
    fun reset() {
        cpu.reset()
        for (i in 0 until 4096) ram.write(i, 0)
    }
}
