package core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class ScreenTest {

    @Test
    fun `write updates correct position`() {
        Screen.clear()
        Screen.write(3, 2, 65) // ASCII 'A'
        val snapshot = Screen.snapshot()
        assertEquals('A', snapshot[2][3])
        // everything else should still be '0'
        assertTrue(snapshot.all { row -> row.all { it == '0' || it == 'A' } })
    }

    @Test
    fun `clear resets all positions to 0`() {
        Screen.write(1, 1, 66) // 'B'
        Screen.clear()
        val snapshot = Screen.snapshot()
        assertTrue(snapshot.all { row -> row.all { it == '0' } })
    }

    @Test
    fun `snapshot reflects buffer changes`() {
        Screen.clear()
        Screen.write(0, 0, 67) // 'C'
        val snapshot = Screen.snapshot()
        assertEquals('C', snapshot[0][0])
        assertEquals('0', snapshot[0][1])
    }

    @Test
    fun `multiple writes appear correctly in snapshot`() {
        Screen.clear()
        Screen.write(0, 0, 88) // 'X'
        Screen.write(7, 7, 89) // 'Y'
        val snapshot = Screen.snapshot()
        assertEquals('X', snapshot[0][0])
        assertEquals('Y', snapshot[7][7])
    }
}
