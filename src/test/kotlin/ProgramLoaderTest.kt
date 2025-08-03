package core

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import java.io.File

class ProgramLoaderTest {

    @Test
    fun `throws exception if file does not exist`() {
        val loader = ProgramLoader()
        val fakePath = "roms/this_file_does_not_exist.out"

        val exception = assertThrows(IllegalArgumentException::class.java) {
            loader.loadOutFile(fakePath)
        }
        assertTrue(exception.message!!.contains("File not found"))
    }

    @Test
    fun `loads existing rom file into int array`() {
        val loader = ProgramLoader()
        val romFile = File("roms/hello.out")
        assertTrue(romFile.exists(), "Expected roms/hello.out to exist for test")

        val result = loader.loadOutFile(romFile.path)

        assertTrue(result.isNotEmpty(), "ROM should not be empty")
        assertEquals(romFile.readBytes()[0].toInt() and 0xFF, result[0])
    }

    @Test
    fun `all loaded bytes are unsigned`() {
        val loader = ProgramLoader()
        val romFile = File("roms/hello.out")
        assertTrue(romFile.exists(), "Expected roms/hello.out to exist for test")

        val result = loader.loadOutFile(romFile.path)

        // All bytes should be between 0 and 255
        assertTrue(result.all { it in 0..255 }, "All bytes must be unsigned Ints")
    }
}
