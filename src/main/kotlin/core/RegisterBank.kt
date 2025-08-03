package core

class RegisterBank {
    private val gpr = IntArray(8)

    var p: Int = 0
        set(value) { field = value and 0xFFFF }

    var t: Int = 0
        set(value) { field = value and 0xFF }

    var a: Int = 0
        set(value) { field = value and 0xFFFF }

    var m: Int = 0
        set(value) { field = value and 0x1 } // only 0 or 1

    operator fun get(index: Int): Int = gpr[index] and 0xFF
    operator fun set(index: Int, value: Int) { gpr[index] = value and 0xFF }

    fun reset() {
        gpr.fill(0)
        p = 0
        t = 0
        a = 0
        m = 0
    }
}
