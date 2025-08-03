package memory

class RAM : Memory {
    private val data = IntArray(4096)

    override fun read(address: Int): Int =
        data[address % data.size] and 0xFF

    override fun write(address: Int, value: Int) {
        data[address % data.size] = value and 0xFF
    }

    fun clear() {
        data.fill(0)
    }
}
