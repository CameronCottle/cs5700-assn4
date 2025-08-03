package core

class RegisterBank {
    private val gpr = IntArray(8)

    // Backing fields
    private var _p: Int = 0
    private var _t: Int = 0
    private var _a: Int = 0

    var p: Int
        get() = _p
        set(value) { _p = value and 0xFFFF }

    var t: Int
        get() = _t
        set(value) { _t = value and 0xFF }

    var a: Int
        get() = _a
        set(value) { _a = value and 0xFFFF }

    var m: Boolean = false

    /** Enables registerBank[index] syntax */
    operator fun get(index: Int): Int {
        return gpr[index] and 0xFF
    }

    operator fun set(index: Int, value: Int) {
        gpr[index] = value and 0xFF
    }

    fun reset() {
        for (i in gpr.indices) gpr[i] = 0
        p = 0
        t = 0
        a = 0
        m = false
    }
}
