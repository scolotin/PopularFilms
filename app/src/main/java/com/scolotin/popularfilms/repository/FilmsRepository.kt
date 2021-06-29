package com.scolotin.popularfilms.repository

import com.scolotin.popularfilms.model.Film
import io.reactivex.rxjava3.core.Single

interface FilmsRepository {

    fun fetchPopularFilms(): Single<List<Film>>

}
