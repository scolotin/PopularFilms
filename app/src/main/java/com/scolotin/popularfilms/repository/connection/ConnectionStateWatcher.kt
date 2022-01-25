package com.scolotin.popularfilms.repository.connection

import io.reactivex.Observable

interface ConnectionStateWatcher {

    fun watchForConnectionState(): Observable<ConnectionState>

}
