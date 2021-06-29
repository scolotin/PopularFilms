package com.scolotin.popularfilms.di.modules

import com.scolotin.popularfilms.repository.datasource.DataSource
import com.scolotin.popularfilms.repository.datasource.NetworkDataSource
import com.scolotin.popularfilms.repository.FilmsRepository
import com.scolotin.popularfilms.repository.FilmsRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface FilmsModule {

    @Singleton
    @Binds
    fun bindFilmsRepository(filmsRepository: FilmsRepositoryImpl): FilmsRepository

    @Singleton
    @Binds
    fun bindNetworkDataSource(networkDataSource: NetworkDataSource): DataSource

}
