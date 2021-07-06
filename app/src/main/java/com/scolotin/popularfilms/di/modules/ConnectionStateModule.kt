package com.scolotin.popularfilms.di.modules

import android.content.Context
import com.scolotin.popularfilms.repository.connection.ConnectionStateWatcher
import com.scolotin.popularfilms.repository.connection.ConnectionStateWatcherImpl
import dagger.Module
import dagger.Provides

@Module
class ConnectionStateModule {

    @Provides
    fun provideConnectionStateWatcher(context: Context): ConnectionStateWatcher =
        ConnectionStateWatcherImpl(context)

}
