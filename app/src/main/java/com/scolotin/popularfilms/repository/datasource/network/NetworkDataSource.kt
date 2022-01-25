package com.scolotin.popularfilms.repository.datasource.network

import com.scolotin.popularfilms.BuildConfig.API_KEY
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.repository.api.TmdbApi
import com.scolotin.popularfilms.repository.datasource.DataSource
import io.reactivex.Single
import javax.inject.Inject

class NetworkDataSource @Inject constructor(private val tmdbApi: TmdbApi) : DataSource {

    override fun fetchPopularFilms(): Single<List<Film>> =
        tmdbApi
            .getPopularFilms(API_KEY)
            .flatMap {
                Single.just(it.results)
            }

}
