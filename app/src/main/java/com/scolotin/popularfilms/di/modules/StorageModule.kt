package com.scolotin.popularfilms.di.modules

import android.content.Context
import androidx.room.Room
import com.scolotin.popularfilms.repository.storage.PopularFilmsStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StorageModule {

    @Singleton
    @Provides
    fun providePopularFilmsStorage(context: Context): PopularFilmsStorage =
        Room
            .databaseBuilder(context, PopularFilmsStorage::class.java, "films.db")
            .build()

}
