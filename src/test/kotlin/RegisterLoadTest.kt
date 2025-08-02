package core

import instructions.InstructionFactory
import memory.RAM
import memory.ROM
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RegisterLoadTest {

    private val factory = InstructionFactory()

    @Test
    fun testHelloRegistersLoad() {
        val cpu = CPU(RAM(), ROM(IntArray(4096)))

        // Simulate the first 5 STORE instructions from hello.out
        val program = listOf(
            0x00 to 0x48,  // r0 <- 'H'
            0x01 to 0x45,  // r1 <- 'E'
            0x02 to 0x4C,  // r2 <- 'L'
            0x03 to 0x4C,  // r3 <- 'L'
            0x04 to 0x4F   // r4 <- 'O'
        )

        for ((b1, b2) in program) {
            val instr = factory.createInstruction(b1, b2)
            instr.execute(cpu)
        }

        // Verify the register values match ASCII codes
        assertEquals(0x48, cpu.registers.get(0)) // H
        assertEquals(0x45, cpu.registers.get(1)) // E
        assertEquals(0x4C, cpu.registers.get(2)) // L
        assertEquals(0x4C, cpu.registers.get(3)) // L
        assertEquals(0x4F, cpu.registers.get(4)) // O
    }
}
