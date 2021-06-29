package com.scolotin.popularfilms.repository

import com.scolotin.popularfilms.repository.datasource.DataSource
import com.scolotin.popularfilms.model.Film
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val networkDataSource: DataSource
) : FilmsRepository {

    override fun fetchPopularFilms(): Single<List<Film>> =
        networkDataSource.fetchPopularFilms()

}
