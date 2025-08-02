package core

import java.io.File

/**
 * Reads .out files and converts them into an IntArray for ROM.
 * Handles nibble extraction and any file format quirks.
 */
class ProgramLoader {
    fun loadOutFile(path: String): IntArray {
        val file = File(path)
        if (!file.exists()) throw IllegalArgumentException("File not found: $path")

        return file.readBytes().map { it.toInt() and 0xFF }.toIntArray()
    }
}
