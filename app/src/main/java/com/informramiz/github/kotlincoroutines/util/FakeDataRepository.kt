package com.informramiz.github.kotlincoroutines.util

import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by Ramiz Raja on 19/12/2018
 */
class FakeDataRepository {
    private val fakeNetworkSource = FakeNetworkSource()

    suspend fun getFirstName(): String {
        return suspendCoroutine { continuation ->
            fakeNetworkSource.fetchFirstName {
                continuation.resume(it)
            }
        }
    }

    suspend fun getLastName(): String {
        return suspendCoroutine { continuation ->
            fakeNetworkSource.fetchLastName {
                continuation.resume(it)
            }
        }
    }
}