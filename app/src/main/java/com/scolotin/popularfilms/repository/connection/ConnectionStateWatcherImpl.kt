package com.scolotin.popularfilms.repository.connection

import android.content.Context
import io.reactivex.rxjava3.core.Observable

class ConnectionStateWatcherImpl(private val context: Context) : ConnectionStateWatcher {

    override fun watchForConnectionState(): Observable<ConnectionState> =
        ConnectionStateObservable(context)
            .cacheWithInitialCapacity(2)

}
