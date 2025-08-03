package core

import observer.ScreenObserver
import observer.ScreenSubject

class Screen private constructor() : ScreenSubject {

    private val buffer = Array(8) { CharArray(8) { '0' } }
    private val observers = mutableListOf<ScreenObserver>()

    override fun addObserver(observer: ScreenObserver) {
        observers.add(observer)
    }

    override fun removeObserver(observer: ScreenObserver) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        val snapshot = snapshot()
        observers.forEach { it.onScreenUpdate(snapshot) }
    }

    fun write(x: Int, y: Int, char: Char) {
        buffer[y][x] = char
        notifyObservers()
    }

    fun clear() {
        for (row in buffer) row.fill('0')
    }

    fun snapshot(): Array<CharArray> =
        buffer.map { it.copyOf() }.toTypedArray()

    companion object {
        val instance: Screen by lazy { Screen() }
    }
}

