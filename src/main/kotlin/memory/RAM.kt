package memory

import core.EmulatorFacade

class RAM : Memory {
    private val data = IntArray(4096)
    val screen = CharArray(64) { '0' } // 8x8 display

    override fun read(address: Int): Int {
        return data[address % data.size] and 0xFF
    }

    override fun write(address: Int, value: Int) {
        data[address % data.size] = value and 0xFF
        if (address in 0 until 64) { // update screen
            screen[address] = if (value == 0) '0' else value.toChar()
        }
    }

    fun renderScreen() {
        for (row in 0 until 8) {
            val start = row * 8
            println(screen.concatToString(start, start + 8))
        }
        println("========")
    }
}

