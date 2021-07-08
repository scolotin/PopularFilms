package com.scolotin.popularfilms.di.modules

import com.scolotin.popularfilms.repository.connection.ConnectionStateWatcher
import com.scolotin.popularfilms.repository.connection.ConnectionStateWatcherImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ConnectionStateModule {

    @Singleton
    @Binds
    fun bindConnectionStateWatcher(connectionStateWatcher: ConnectionStateWatcherImpl): ConnectionStateWatcher

}
