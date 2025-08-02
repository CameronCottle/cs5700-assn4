import core.CPU
import core.Screen
import memory.RAM
import memory.ROM
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CPUTest {

    private lateinit var cpu: CPU

    @BeforeEach
    fun setup() {
        cpu = CPU(RAM(), ROM(IntArray(16)))
        cpu.reset()
        Screen.instance.clear()
    }

    @Test
    fun `CPU reset should zero registers and clear screen`() {
        cpu.registers.a = 5
        cpu.registers.p = 3
        cpu.getScreen().write(0, 0, 'X')

        cpu.reset()

        assertEquals(0, cpu.registers.a)
        assertEquals(0, cpu.registers.p)
        assertEquals('0', cpu.getScreen().snapshot()[0][0])
    }

//    @Test
//    fun `CPU should halt on F0 00 instruction`() {
//        // Write HALT instruction to ROM
//        val rom = ROM(intArrayOf(0xF0, 0x00))
//        cpu.reset(rom)
//
//        val halted = cpu.step()
//        assertTrue(halted)
//    }
}
