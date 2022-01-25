package com.scolotin.popularfilms.repository.datasource

import com.scolotin.popularfilms.model.Film
import io.reactivex.Single

interface DataSource {

    fun fetchPopularFilms(): Single<List<Film>>

}
