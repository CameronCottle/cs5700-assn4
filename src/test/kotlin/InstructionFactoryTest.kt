package instructions

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import instructions.opcodes.*

class InstructionFactoryTest {

    private val factory = InstructionFactory()

    @Test
    fun `creates HaltInstruction for 0x00 0x00`() {
        val inst = factory.createInstruction(0x00, 0x00)
        assertTrue(inst is HaltInstruction)
    }

    @Test
    fun `creates correct instruction types for each opcode`() {
        val cases = listOf(
            0x00 to StoreInstruction::class,
            0x10 to AddInstruction::class,
            0x20 to SubInstruction::class,
            0x30 to ReadInstruction::class,
            0x40 to WriteInstruction::class,
            0x50 to JumpInstruction::class,
            0x60 to ReadKeyboardInstruction::class,
            0x70 to SwitchMemoryInstruction::class,
            0x80 to SkipEqualInstruction::class,
            0xA0 to SetAInstruction::class,
            0xB0 to SetTInstruction::class,
            0xC0 to ReadTInstruction::class,
            0xD0 to ConvertToBase10Instruction::class,
            0xE0 to ConvertByteToAsciiInstruction::class,
            0xF0 to DrawInstruction::class
        )

        for ((byte1, expectedClass) in cases) {
            val inst = factory.createInstruction(byte1, 0x12)
            assertEquals(expectedClass, inst::class, "Opcode ${(byte1 shr 4).toString(16)} should map to ${expectedClass.simpleName}")
        }
    }

    @Test
    fun `throws on invalid opcode`() {
        val invalidByte1 = 0x90 // SkipNotEqualInstruction commented out
        assertThrows(IllegalArgumentException::class.java) {
            factory.createInstruction(invalidByte1, 0x34)
        }
    }
}
