package com.scolotin.popularfilms.repository.datasource.cache

import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.repository.storage.PopularFilmsStorage
import io.reactivex.Single
import javax.inject.Inject

class CacheDataSourceImpl
@Inject constructor(
    popularFilmsStorage: PopularFilmsStorage
) : CacheDataSource {

    private val storageDao =
        popularFilmsStorage
            .getPopularFilmsDao()

    override fun fetchPopularFilms(): Single<List<Film>> =
        storageDao
            .fetchFilms()

    override fun retain(films: List<Film>): Single<List<Film>> =
        storageDao
            .retain(films)
            .andThen(fetchPopularFilms())

}
