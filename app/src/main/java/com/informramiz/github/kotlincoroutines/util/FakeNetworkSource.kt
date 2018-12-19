package com.informramiz.github.kotlincoroutines.util

import android.support.annotation.MainThread
import android.support.annotation.WorkerThread

/**
 * Created by Ramiz Raja on 19/12/2018
 */
class FakeNetworkSource {
    @MainThread
    fun fetchFirstName(callback: (String) -> Unit) {
        BACKGROUND.execute {
            Thread.sleep(1000)
            MAIN.execute { callback("Ramiz") }
        }
    }

    @MainThread
    fun fetchLastName(callback: (String) -> Unit) {
        BACKGROUND.execute {
            Thread.sleep(1000)
            MAIN.execute { callback("Raja") }
        }
    }
}