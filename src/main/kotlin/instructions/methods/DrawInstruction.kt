//package instructions.methods
//
//import core.CPU
//import instructions.Instruction
//
//class DrawInstruction(byte1: Int, byte2: Int) : Instruction(byte1, byte2) {
//    override fun execute(cpu: CPU) {
//        val rX = byte1 and 0x0F           // register holding ASCII value
//        val rY = (byte2 shr 4) and 0x0F   // register for row
//        val rZ = byte2 and 0x0F           // register for col
//
//        val charValue = cpu.registers.get(rX)
//        val row = cpu.registers.get(rY)
//        val col = cpu.registers.get(rZ)
//
//        if (charValue > 0x7F)
//            throw IllegalStateException("Cannot draw non-ASCII character $charValue")
//
//        // 1. Write to buffer
//        cpu.screen.write(col, row, charValue.toChar())
//
//        // 2. Print entire screen as a frame
//        cpu.screen.draw()
//
//        // 3. Move PC to next instruction
//        cpu.registers.p += 2
//    }
//}
