package core

import memory.RAM
import memory.ROM
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File

class CPUTest {

    @Test
    fun `reset clears registers and halts flag`() {
        val cpu = CPU(RAM(), ROM(IntArray(4)))
        cpu.halt() // set halted to true
        cpu.getRegisters().a = 5
        cpu.getRegisters().p = 3

        cpu.reset()

        assertEquals(0, cpu.getRegisters().a)
        assertEquals(0, cpu.getRegisters().p)
        assertFalse(cpu.halted)
    }

    @Test
    fun `read and write RAM`() {
        val ram = RAM()
        val cpu = CPU(ram, ROM(IntArray(4)))

        cpu.writeRAM(10, 123)
        assertEquals(123, cpu.readRAM(10))
    }

    @Test
    fun `halt sets halted true and returns true`() {
        val cpu = CPU(RAM(), ROM(IntArray(4)))
        val result = cpu.halt()

        assertTrue(result)
        assertTrue(cpu.halted)
    }

    @Test
    fun `step executes an instruction without crashing`() {
        val ram = RAM()
        val romFile = File("roms/hello.out")
        assertTrue(romFile.exists(), "Expected roms/hello.out to exist")

        val bytes = romFile.readBytes().map { it.toInt() and 0xFF }.toIntArray()
        val cpu = CPU(ram, ROM(bytes))

        // Step several times and ensure no crash
        repeat(5) {
            assertDoesNotThrow { cpu.step() }
        }
    }

    @Test
    fun `step eventually halts when PC goes out of range`() {
        // Tiny ROM to force out-of-range after first instruction
        val tinyRom = ROM(intArrayOf(0x01, 0x02)) // arbitrary bytes
        val cpu = CPU(RAM(), tinyRom)

        // After 2 steps, PC will be out of range → halt
        repeat(2) { cpu.step() }

        assertTrue(cpu.halted, "CPU should halt when PC goes past ROM size")
    }
}
