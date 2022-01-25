package com.scolotin.popularfilms.repository.datasource

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.scolotin.popularfilms.model.Film
import com.scolotin.popularfilms.model.Films
import com.scolotin.popularfilms.repository.api.TmdbApi
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PagingDataSource @Inject constructor(
    private val tmdbApi: TmdbApi
) : RxPagingSource<Int, Film>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Film>> {
        val position = params.key ?: 1

        return tmdbApi.getPopularFilms("74c34ef78210a9bc3840d6c543b19fa4", page = position)
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(it, position)
            }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: Films, position: Int): LoadResult<Int, Film> {
        return LoadResult.Page(
            data = data.results,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.results.size) null else position + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition
    }

}
