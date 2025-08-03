package core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class TimerTest {

    @Test
    fun `timer decrements t register over time`() {
        val registers = RegisterBank()
        val timer = Timer(registers)

        registers.t = 3
        timer.start()

        // Wait enough for multiple ticks (16ms * 3 = 48ms)
        Thread.sleep(100)

        timer.stop()
        assertTrue(registers.t < 3, "Timer should decrement t over time")
        assertTrue(registers.t >= 0, "t should not go negative")
    }

    @Test
    fun `timer stops decrementing after stop`() {
        val registers = RegisterBank()
        val timer = Timer(registers)

        registers.t = 2
        timer.start()
        Thread.sleep(50)
        timer.stop()

        val valueAfterStop = registers.t
        Thread.sleep(50)

        assertEquals(valueAfterStop, registers.t, "t should not change after timer stops")
    }
}
