package com.scolotin.popularfilms.di.modules

import com.scolotin.popularfilms.di.Cache
import com.scolotin.popularfilms.di.Network
import com.scolotin.popularfilms.repository.datasource.DataSource
import com.scolotin.popularfilms.repository.datasource.network.NetworkDataSource
import com.scolotin.popularfilms.repository.FilmsRepository
import com.scolotin.popularfilms.repository.FilmsRepositoryImpl
import com.scolotin.popularfilms.repository.datasource.cache.CacheDataSource
import com.scolotin.popularfilms.repository.datasource.cache.CacheDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface FilmsModule {

    @Singleton
    @Binds
    fun bindFilmsRepository(filmsRepository: FilmsRepositoryImpl): FilmsRepository

    @Network
    @Singleton
    @Binds
    fun bindNetworkDataSource(networkDataSource: NetworkDataSource): DataSource

    @Cache
    @Singleton
    @Binds
    fun bindCacheDataSource(cacheDataSource: CacheDataSourceImpl): CacheDataSource

}
