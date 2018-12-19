package com.informramiz.github.kotlincoroutines.ui.main

import android.arch.lifecycle.ViewModel
import android.util.Log
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun printAfterDelay() {
        viewModelScope.launch {
            printLog()
        }
    }

    private suspend fun printLog() {
        delay(1000) //suspend for 1 second
        Log.i(MainViewModel::class.java.simpleName, "1 second passed")
    }
}
