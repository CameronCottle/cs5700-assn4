
import memory.RAM
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RAMTest {

    @Test
    fun `test store and retrieve values`() {
        val ram = RAM()
        ram.write(0, 123)
        assertEquals(123, ram.read(0))
    }

    @Test
    fun `test values wrap to 8-bit`() {
        val ram = RAM()
        ram.write(0, 300)
        assertEquals(44, ram.read(0)) // 300 & 0xFF
    }

    @Test
    fun `test address wrap`() {
        val ram = RAM()
        val lastAddr = 4095
        ram.write(lastAddr + 1, 200)
        assertEquals(200, ram.read(0))
    }
}
