package core

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class Timer(private val registerBank: RegisterBank) {
    private val scheduler = Executors.newSingleThreadScheduledExecutor()
    @Volatile private var running = false

    fun start() {
        if (!running) {
            running = true
            scheduler.scheduleAtFixedRate({
                if (registerBank.t > 0) {
                    registerBank.t--
                }
            }, 0, 16, TimeUnit.MILLISECONDS) // ~60Hz
        }
    }

    fun stop() {
        running = false
        scheduler.shutdownNow()
    }
}