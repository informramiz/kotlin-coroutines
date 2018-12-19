
package com.informramiz.github.kotlincoroutines.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

val BACKGROUND = Executors.newFixedThreadPool(2)
val MAIN = object : Executor {
    private val mainThreadHandler = Handler(Looper.getMainLooper())
    override fun execute(command: Runnable) {
        mainThreadHandler.post(command)
    }

}
