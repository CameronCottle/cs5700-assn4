package core

object NibbleParser {
    fun highNibble(byte: Int) = (byte shr 4) and 0xF
    fun lowNibble(byte: Int) = byte and 0xF
}