package com.informramiz.github.kotlincoroutines.ui.main

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.informramiz.github.kotlincoroutines.R
import com.informramiz.github.kotlincoroutines.util.FakeDataRepository
import kotlinx.coroutines.*

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val dateRepository = FakeDataRepository()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun printAfterDelay() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                printLog()
            }
        }
    }

    private suspend fun printLog() {
        delay(1000) //suspend for 1 second
        Log.i(MainViewModel::class.java.simpleName, "1 second passed")
    }

    fun fetchFullName(): LiveData<String> {
        val fullNameLiveData = MutableLiveData<String>()
        viewModelScope.launch {
            val firstName = dateRepository.getFirstName()
            val lastName = dateRepository.getLastName()
            fullNameLiveData.value = this@MainViewModel.getApplication<Application>()
                .applicationContext.getString(R.string.full_name_format, firstName, lastName)
        }

        return fullNameLiveData
    }
}
