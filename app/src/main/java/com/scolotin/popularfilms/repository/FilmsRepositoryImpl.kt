package com.scolotin.popularfilms.repository

import com.scolotin.popularfilms.di.Cache
import com.scolotin.popularfilms.di.Network
import com.scolotin.popularfilms.repository.datasource.DataSource
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.repository.datasource.cache.CacheDataSource
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    @Network private val networkDataSource: DataSource,
    @Cache private val cacheDataSource: CacheDataSource
) : FilmsRepository {

    override fun fetchPopularFilms(): Single<List<Film>> =
        cacheDataSource
            .fetchPopularFilms()
            .flatMap(::fetchFromNetwork)

    private fun fetchFromNetwork(films: List<Film>): Single<List<Film>> =
        if (films.isEmpty()) {
            networkDataSource
                .fetchPopularFilms()
                .flatMap(cacheDataSource::retain)
        } else {
            Single.just(films)
        }

}
