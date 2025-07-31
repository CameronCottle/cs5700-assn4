import memory.ROM
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ROMTest {

    @Test
    fun `test read values`() {
        val rom = ROM(intArrayOf(10, 20, 30))
        assertEquals(10, rom.read(0))
        assertEquals(30, rom.read(2))
    }

    @Test
    fun `test address wrap`() {
        val rom = ROM(intArrayOf(1, 2, 3))
        assertEquals(2, rom.read(4)) // 4 % 3 = 1
    }

    @Test
    fun `test write throws exception`() {
        val rom = ROM(intArrayOf(0, 1, 2))
        assertThrows<UnsupportedOperationException> {
            rom.write(0, 123)
        }
    }
}
