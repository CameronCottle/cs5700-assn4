package memory

interface Memory {
    fun read(address: Int): Int
    fun write(address: Int, value: Int)
}