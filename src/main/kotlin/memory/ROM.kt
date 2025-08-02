package memory

class ROM(private val data: IntArray) : Memory {
    fun size() = data.size

    override fun read(address: Int): Int {
        return data[address] and 0xFF
    }

    override fun write(address: Int, value: Int) {
        throw UnsupportedOperationException("Cannot write to ROM")
    }
}
