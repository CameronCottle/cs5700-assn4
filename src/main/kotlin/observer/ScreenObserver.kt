package observer

// Observer interface (subscriber)
interface ScreenObserver {
    fun onScreenUpdate(screen: Array<CharArray>)
}