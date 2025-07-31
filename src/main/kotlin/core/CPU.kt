package core

import instructions.InstructionFactory
import memory.Memory

class CPU(
    val ram: Memory,
    val rom: Memory,
//    val screen: Screen
) {
    val registers = RegisterBank()

    // execution loop coming soon
    fun step() {
        // fetch 2 bytes from ROM or RAM depending on m register
        val pc = registers.p
        val mem = if (registers.m) rom else ram
        val byte1 = mem.read(pc)
        val byte2 = mem.read(pc + 1)

        // parse and execute
        val instruction = InstructionFactory().createInstruction(byte1, byte2)
        instruction.execute(this)
    }
}
