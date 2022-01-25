package com.scolotin.popularfilms.repository.datasource

import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.repository.api.TmdbApi
import io.reactivex.Single
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val tmdbApi: TmdbApi
) : DataSource {

    override fun fetchPopularFilms(): Single<List<Film>> =
        tmdbApi
            .getPopularFilms("74c34ef78210a9bc3840d6c543b19fa4")
            .flatMap {
                Single.just(it.results)
            }

}
