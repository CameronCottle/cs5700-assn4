package core

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.nio.file.Files
import java.nio.file.Path

class EmulatorFacadeTest {

    private lateinit var facade: EmulatorFacade

    @BeforeEach
    fun setup() {
        facade = EmulatorFacade()
    }

    @Test
    fun `run eventually halts CPU`() {
        // Program: 2 NOP-like instructions then halt
        val program = intArrayOf(
            0x10, 0x00, // nop-like (safe)
            0x10, 0x01, // another safe instruction
            0x00, 0x00  // halt
        )
        val tmpFile = Path.of("build/tmp_test_program2.out")
        Files.createDirectories(tmpFile.parent)
        Files.write(tmpFile, program.joinToString("\n").toByteArray())

        facade.loadProgram(tmpFile.toString())

        assertDoesNotThrow {
            facade.run() // should finish since program halts
        }
    }

    @Test
    fun `can run a small non-halt program for several steps`() {
        val program = intArrayOf(
            0x10, 0x00, // safe instruction
            0x10, 0x01, // safe instruction
            0x10, 0x02, // safe instruction
            0x00, 0x00  // halt
        )
        val tmpFile = Path.of("build/tmp_test_program3.out")
        Files.createDirectories(tmpFile.parent)
        Files.write(tmpFile, program.joinToString("\n").toByteArray())

        facade.loadProgram(tmpFile.toString())

        var halted = false
        var steps = 0
        while (!halted && steps < 10) {
            halted = facade.step()
            steps++
        }

        assertTrue(halted, "CPU should halt eventually")
        assertTrue(steps in 2..10, "CPU should have run multiple instructions before halting")
    }
}
