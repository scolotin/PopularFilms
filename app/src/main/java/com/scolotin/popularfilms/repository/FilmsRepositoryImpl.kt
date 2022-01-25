package com.scolotin.popularfilms.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava2.flowable
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.repository.datasource.PagingDataSource
import io.reactivex.Flowable
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val pagingDataSource: PagingDataSource
) : FilmsRepository {

    override fun fetchPopularFilms(): Flowable<PagingData<Film>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                maxSize = 30,
                prefetchDistance = 5,
                initialLoadSize = 40),
            pagingSourceFactory = { pagingDataSource }
        ).flowable
    }

}
