package cli

import core.EmulatorFacade

fun main() {
    val system = EmulatorFacade()

    print("Enter path to program file: ")
    val path = readlnOrNull() ?: return

    try {
        system.loadProgram(path)
        system.run()
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}

