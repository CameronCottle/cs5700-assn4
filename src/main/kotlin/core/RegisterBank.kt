package core

class RegisterBank {
    private val general = IntArray(8)

    var t: Int = 0
        set(value) { field = value and 0xFF }

    var a: Int = 0
        set(value) { field = value and 0xFFFF }

    var m: Boolean = false

    var p: Int = 0
        set(value) { field = value and 0xFFFF }

    fun get(index: Int) = general[index] and 0xFF

    fun set(index: Int, value: Int) {
        general[index] = value and 0xFF
    }

    fun getTimer() = t
}
