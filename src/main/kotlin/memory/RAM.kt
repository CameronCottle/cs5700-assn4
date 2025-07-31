package memory

class RAM(private val size: Int = 4096) : Memory {
    private val data = IntArray(size)

    override fun read(address: Int): Int {
        return data[address % size] and 0xFF
    }

    override fun write(address: Int, value: Int) {
        data[address % size] = value and 0xFF
    }
}
