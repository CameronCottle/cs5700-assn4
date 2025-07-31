import core.CPU
import instructions.methods.AddInstruction
import memory.RAM
import memory.ROM
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class InstructionTest {

    @Test
    fun `adds rX and rY and stores in rZ`() {
        val cpu = CPU(RAM(), ROM(IntArray(4096)))
        cpu.registers.set(0, 10)   // r0 = 10
        cpu.registers.set(1, 20)   // r1 = 20

        // ADD r0 + r1 -> r2  (encoded as 1XYZ = 1 0 1 2)
        val instr = AddInstruction(0x10, 0x12)
        instr.execute(cpu)

        assertEquals(30, cpu.registers.get(2))   // 10 + 20 = 30
        assertEquals(2, cpu.registers.p)         // PC incremented by 2
    }

    @Test
    fun `addition wraps around 8-bit`() {
        val cpu = CPU(RAM(), ROM(IntArray(4096)))
        cpu.registers.set(0, 250) // 0xFA
        cpu.registers.set(1, 10)  // 0x0A

        // ADD r0 + r1 -> r2
        val instr = AddInstruction(0x01, 0x02) // rX=1, rY=0, rZ=2
        instr.execute(cpu)

        assertEquals((250 + 10) and 0xFF, cpu.registers.get(2))
        assertEquals(2, cpu.registers.p)
    }
}
