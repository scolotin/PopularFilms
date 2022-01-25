package com.scolotin.popularfilms.repository.connection

import android.content.Context
import io.reactivex.Observable
import javax.inject.Inject

class ConnectionStateWatcherImpl @Inject constructor(
    private val context: Context
) : ConnectionStateWatcher {

    override fun watchForConnectionState(): Observable<ConnectionState> =
        ConnectionStateObservable(context)
            .cacheWithInitialCapacity(2)

}
