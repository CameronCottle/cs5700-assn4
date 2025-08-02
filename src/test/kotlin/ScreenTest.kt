import core.Screen
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScreenTest {

    private val screen = Screen.instance

    @BeforeEach
    fun resetScreen() {
        screen.clear()
    }

    @Test
    fun `screen should be empty after clear`() {
        val snapshot = screen.snapshot()
        for (row in snapshot) {
            for (cell in row) {
                assertEquals('0', cell)
            }
        }
    }

    @Test
    fun `screen write should update the buffer`() {
        screen.write(0, 0, 'H')
        val snapshot = screen.snapshot()
        assertEquals('H', snapshot[0][0])
    }

    @Test
    fun `screen write outside bounds should not throw`() {
        screen.write(10, 10, 'X') // Out of bounds
        // Just verify nothing crashed
        assertTrue(true)
    }
}
