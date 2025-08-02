package core

class Screen private constructor() {
    private val buffer = Array(8) { CharArray(8) { '0' } }

    fun write(x: Int, y: Int, char: Char) {
        if (x in 0..7 && y in 0..7) buffer[y][x] = char
    }

    fun clear() {
        for (y in 0..7) for (x in 0..7) buffer[y][x] = '0'
    }

    fun draw() {
        for (row in buffer) println(row.concatToString())
        println("========")
    }

    fun snapshot(): Array<CharArray> = buffer.map { it.clone() }.toTypedArray()

    companion object {
        val instance: Screen by lazy { Screen() }
    }
}
