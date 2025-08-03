package core

import memory.RAM
import memory.ROM
import observer.ScreenObserver

class EmulatorFacade : ScreenObserver {

    private val ram = RAM()
    private var rom = ROM(IntArray(4096))
    private val cpu = CPU(ram, rom)
    private val loader = ProgramLoader()
    private val screen = Screen.instance

    init {
        screen.addObserver(this)  // Subscribe to screen updates
    }

    override fun onScreenUpdate(screen: Array<CharArray>) {
        drawScreen(screen)
    }

    fun loadProgram(path: String) {
        val program = loader.loadOutFile(path)
        println("Loaded program with ${program.size} nibbles")

        rom = ROM(program)
        cpu.reset(rom)
        screen.clear()
    }

    fun step(): Boolean = cpu.step()

    fun run(maxSteps: Int = 1000) {
        repeat(maxSteps) { step ->
            if (step()) return
        }
        println("Max steps reached ($maxSteps) without halt.")
    }

    private fun drawScreen(screenData: Array<CharArray>) {
        for (row in screenData) {
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

