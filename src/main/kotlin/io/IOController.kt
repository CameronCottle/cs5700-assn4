package io

import java.util.Scanner

class IOController(private val display: Display) {
    /** Blocking keyboard input (hex digit 0-F) */
    fun readHexInput(): Int {
        print("Enter hex digit (0-F): ")
        val input = readlnOrNull()?.trim()?.uppercase() ?: return 0
        return input.toIntOrNull(16) ?: 0
    }

    fun draw(row: Int, col: Int, ascii: Int) {
        display.drawChar(row, col, ascii)
    }
}