package com.scolotin.popularfilms.repository.connection

import io.reactivex.rxjava3.core.Observable

interface ConnectionStateWatcher {

    fun watchForConnectionState(): Observable<ConnectionState>

}
