package core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class RegisterBankTest {

    @Test
    fun `general purpose registers mask to 8 bits`() {
        val regs = RegisterBank()
        regs[0] = 0x1FF // 511 in decimal, 0x1FF in hex -> only lower 8 bits should be kept
        assertEquals(0xFF, regs[0])
    }

    @Test
    fun `program counter masks to 16 bits`() {
        val regs = RegisterBank()
        regs.p = 0x1FFFF // 131071 -> lower 16 bits should remain
        assertEquals(0xFFFF, regs.p)
    }

    @Test
    fun `T register masks to 8 bits`() {
        val regs = RegisterBank()
        regs.t = 0x1FF // should mask to 0xFF
        assertEquals(0xFF, regs.t)
    }

    @Test
    fun `A register masks to 16 bits`() {
        val regs = RegisterBank()
        regs.a = 0x1FFFF
        assertEquals(0xFFFF, regs.a)
    }

    @Test
    fun `M register masks to 1 bit`() {
        val regs = RegisterBank()
        regs.m = 5 // 0b101 -> only least significant bit should remain
        assertEquals(1, regs.m)
        regs.m = 2 // 0b10 -> only LSB remains
        assertEquals(0, regs.m)
    }

    @Test
    fun `reset clears all registers`() {
        val regs = RegisterBank()
        regs[0] = 0xFF
        regs.p = 0xFFFF
        regs.t = 0xFF
        regs.a = 0xFFFF
        regs.m = 1

        regs.reset()

        assertEquals(0, regs[0])
        assertEquals(0, regs.p)
        assertEquals(0, regs.t)
        assertEquals(0, regs.a)
        assertEquals(0, regs.m)
    }
}
