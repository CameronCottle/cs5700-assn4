package core

object Screen {

    // internal 64-byte RAM (0x00 = blank)
    private val buffer = ByteArray(64) { 0 }

    fun write(x: Int, y: Int, value: Int) {
        buffer[y * 8 + x] = value.toByte()
    }

    fun clear() {
        buffer.fill(0)
    }

    fun snapshot(): Array<CharArray> =
        Array(8) { row ->
            CharArray(8) { col ->
                val byte = buffer[row * 8 + col]
                if (byte.toInt() == 0) '0' else byte.toInt().toChar()
            }
        }

    fun render() {
        for (row in 0 until 8) {
            val line = CharArray(8) { col ->
                val byte = buffer[row * 8 + col].toInt() and 0xFF
                if (byte == 0) '0'
                else byte.toChar()
            }
            println(line.concatToString())
        }
        println("========")
    }
}
