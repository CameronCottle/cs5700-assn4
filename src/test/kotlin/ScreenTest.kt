package core

import observer.ScreenObserver
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ScreenTest {

    private lateinit var screen: Screen

    @BeforeEach
    fun setUp() {
        screen = Screen.instance
        screen.clear()
    }

    @Test
    fun `write updates buffer and snapshot reflects change`() {
        screen.write(2, 3, 'A')

        val snap = screen.snapshot()
        assertEquals('A', snap[3][2], "Snapshot should reflect written character")
    }

    @Test
    fun `clear resets screen to zeroes`() {
        screen.write(0, 0, 'X')
        screen.write(7, 7, 'Y')

        screen.clear()
        val snap = screen.snapshot()

        for (row in snap) {
            for (cell in row) {
                assertEquals('0', cell, "All cells should be reset to '0'")
            }
        }
    }

    @Test
    fun `snapshot returns a deep copy`() {
        screen.write(1, 1, 'B')
        val snap = screen.snapshot()

        // Modify snapshot and ensure original is unaffected
        snap[1][1] = 'Z'
        val newSnap = screen.snapshot()

        assertEquals('B', newSnap[1][1], "Snapshot should be a deep copy, not a reference")
    }

    @Test
    fun `observer is notified on write`() {
        var called = false
        var lastSnapshot: Array<CharArray>? = null

        val observer = object : ScreenObserver {
            override fun onScreenUpdate(screen: Array<CharArray>) {
                called = true
                lastSnapshot = screen
            }
        }

        screen.addObserver(observer)
        screen.write(4, 5, 'C')

        assertTrue(called, "Observer should be notified after write")
        assertNotNull(lastSnapshot, "Observer should receive a snapshot")
        assertEquals('C', lastSnapshot!![5][4], "Observer snapshot should contain the updated character")

        screen.removeObserver(observer)
    }
}
