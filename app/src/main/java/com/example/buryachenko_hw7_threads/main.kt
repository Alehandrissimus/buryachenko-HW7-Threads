package com.example.buryachenko_hw7_threads

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

fun main() {
    var counter = 0L
    val lock = ReentrantLock()

    fun increase() {
        repeat(10000) {
            lock.lock()
            counter += 1
            lock.unlock()

            Thread.sleep(10)
        }
    }

    fun printing() {
        while (true) {
            println("counter = $counter")
            Thread.sleep(1000)
        }
    }

    val executor = Executors.newFixedThreadPool(4)

    executor.execute(::increase)
    executor.execute(::increase)
    executor.execute(::increase)
    executor.execute(::increase)

    Thread(::printing).start()

    executor.shutdown()
    executor.awaitTermination(1, TimeUnit.MINUTES)
}