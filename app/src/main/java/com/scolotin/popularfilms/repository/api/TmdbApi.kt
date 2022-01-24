package com.scolotin.popularfilms.repository.api

import com.scolotin.popularfilms.model.Films
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbApi {

    @GET("3/movie/popular")
    fun getPopularFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Single<Films>

}
