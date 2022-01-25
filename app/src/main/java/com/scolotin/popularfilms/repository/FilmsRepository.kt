package com.scolotin.popularfilms.repository

import androidx.paging.PagingData
import com.scolotin.popularfilms.model.Film
import io.reactivex.Flowable

interface FilmsRepository {

    fun fetchPopularFilms(): Flowable<PagingData<Film>>

}
