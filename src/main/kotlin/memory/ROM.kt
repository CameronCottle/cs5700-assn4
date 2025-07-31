package memory

class ROM(private val data: IntArray) : Memory {
    override fun read(address: Int): Int {
        return data[address % data.size] and 0xFF
    }

    override fun write(address: Int, value: Int) {
        throw UnsupportedOperationException("Cannot write to ROM")
    }
}
