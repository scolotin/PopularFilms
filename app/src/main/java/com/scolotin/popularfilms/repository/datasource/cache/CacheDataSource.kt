package com.scolotin.popularfilms.repository.datasource.cache

import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.repository.datasource.DataSource
import io.reactivex.Single

interface CacheDataSource : DataSource {

    fun retain(films: List<Film>): Single<List<Film>>

}
