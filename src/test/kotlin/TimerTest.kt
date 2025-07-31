import core.RegisterBank
import core.Timer
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TimerTest {

    @Test
    fun `test timer decrements to zero`() {
        val bank = RegisterBank()
        bank.t = 3
        val timer = Timer(bank)

        timer.start()
        Thread.sleep(100) // Wait ~6 ticks
        timer.stop()

        assertEquals(0, bank.t)
    }

    @Test
    fun `test timer does not go below zero`() {
        val bank = RegisterBank()
        bank.t = 1
        val timer = Timer(bank)

        timer.start()
        Thread.sleep(100)
        timer.stop()

        assertEquals(0, bank.t)
    }
}
