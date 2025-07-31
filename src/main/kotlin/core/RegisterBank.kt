package core

class RegisterBank {
    private val general = IntArray(8) // r0-r7

    var t: Int = 0      // Timer (8-bit)
        set(value) { field = value and 0xFF }

    var a: Int = 0      // Address (16-bit)
        set(value) { field = value and 0xFFFF }

    var m: Boolean = false // Memory flag (false=RAM, true=ROM)

    var p: Int = 0      // Program Counter (16-bit)
        set(value) { field = value and 0xFFFF }

    fun get(index: Int) = general[index] and 0xFF

    fun set(index: Int, value: Int) {
        general[index] = value and 0xFF
    }

    fun getTimer() = t
}
