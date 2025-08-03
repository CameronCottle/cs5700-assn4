package core

import java.io.File

class ProgramLoader {
    fun loadOutFile(path: String): IntArray {
        val file = File(path)
        if (!file.exists()) throw IllegalArgumentException("File not found: $path")

        return file.readBytes().map { it.toInt() and 0xFF }.toIntArray()
    }
}
