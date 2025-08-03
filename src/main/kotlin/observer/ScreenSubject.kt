package observer

// Subject interface (observable)
interface ScreenSubject {
    fun addObserver(observer: ScreenObserver)
    fun removeObserver(observer: ScreenObserver)
    fun notifyObservers()
}