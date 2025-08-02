package io

class Display {
    private val buffer = IntArray(64) // 8x8 ASCII display

    fun drawChar(row: Int, col: Int, ascii: Int) {
        require(row in 0..7 && col in 0..7) { "Invalid screen position" }
        buffer[row * 8 + col] = ascii and 0xFF
    }

    fun render() {
        for (r in 0..7) {
            for (c in 0..7) {
                val char = buffer[r * 8 + c].toChar()
                print(char)
            }
            println()
        }
    }
}

