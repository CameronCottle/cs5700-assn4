import core.EmulatorFacade
import core.Screen
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class EmulatorFacadeTest {

    private lateinit var facade: EmulatorFacade

    @BeforeEach
    fun setup() {
        facade = EmulatorFacade()
        Screen.instance.clear()
    }

    @Test
    fun `loadProgram should throw for missing file`() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            facade.loadProgram("nonexistent_file.out")
        }
        assertTrue(exception.message!!.contains("not found"))
    }

    @Test
    fun `loadProgram should load bytes into ROM`() {
        // Create temporary binary file
        val file = File.createTempFile("hello", ".out")
        file.writeBytes(byteArrayOf(0xF0.toByte(), 0x00)) // HALT program

        facade.loadProgram(file.absolutePath)

        // Should not throw and ROM dump should print
        facade.dumpROM(2)
        file.deleteOnExit()
    }

    @Test
    fun `run should halt on halt instruction`() {
        val file = File.createTempFile("halt", ".out")
        file.writeBytes(byteArrayOf(0xF0.toByte(), 0x00))

        facade.loadProgram(file.absolutePath)

        // run will halt almost immediately
        facade.run(10)

        file.deleteOnExit()
    }
}
