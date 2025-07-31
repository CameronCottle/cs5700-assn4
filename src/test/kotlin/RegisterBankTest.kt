package memory
import core.RegisterBank
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RegisterBankTest {

    @Test
    fun `test set and get general registers`() {
        val bank = RegisterBank()
        bank.set(0, 255)
        assertEquals(255, bank.get(0))

        bank.set(1, 300) // Should wrap to 44 (300 & 0xFF)
        assertEquals(44, bank.get(1))
    }

    @Test
    fun `test timer bounds`() {
        val bank = RegisterBank()
        bank.t = 300
        assertEquals(44, bank.t) // 300 & 0xFF
        bank.t = -1
        assertEquals(255, bank.t) // -1 & 0xFF
    }

    @Test
    fun `test program counter and address bounds`() {
        val bank = RegisterBank()
        bank.p = 0x1FFFF
        assertEquals(0xFFFF, bank.p)
        bank.a = 0x1FFFF
        assertEquals(0xFFFF, bank.a)
    }

    @Test
    fun `test memory flag toggles`() {
        val bank = RegisterBank()
        assertFalse(bank.m)
        bank.m = true
        assertTrue(bank.m)
    }
}
