package core

import memory.RAM
import memory.ROM

class EmulatorFacade {

    private val ram = RAM()
    private var rom = ROM(IntArray(4096))
    private val cpu = CPU(ram, rom)
    private val loader = ProgramLoader()

    // loads a program into the rom
    fun loadProgram(path: String) {
        val program = loader.loadOutFile(path)
        rom = ROM(program)
        cpu.reset(rom)
    }

    // step the cpu
    fun step(): Boolean = cpu.step()

    fun run() {
        cpu.startTimer()
        val targetHz = 500
        val sleepTime = 1000L / targetHz
        while (!cpu.halted) {
            step()
            Thread.sleep(sleepTime)
        }
        cpu.stopTimer()
    }
}
