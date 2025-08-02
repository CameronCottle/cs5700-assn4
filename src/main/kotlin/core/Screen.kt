package core

class Screen private constructor() {
    private val width = 8
    private val height = 8
    private val buffer = Array(height) { CharArray(width) { ' ' } } // use spaces

    fun clear() {
        for (row in 0 until height) {
            for (col in 0 until width) {
                buffer[row][col] = ' '
            }
        }
    }

    fun write(x: Int, y: Int, char: Char) {
        if (x in 0 until width && y in 0 until height) {
            buffer[y][x] = char
        }
    }

    fun snapshot(): Array<CharArray> {
        // Convert spaces to 0 for debugging
        return Array(height) { row ->
            CharArray(width) { col ->
                val c = buffer[row][col]
                if (c == ' ') '0' else c
            }
        }
    }

    companion object {
        val instance: Screen by lazy { Screen() }
    }
}
