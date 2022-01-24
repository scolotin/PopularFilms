package com.scolotin.popularfilms.repository.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.scolotin.popularfilms.model.Film
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface PopularFilmsDao {

    @Query("SELECT * FROM films")
    fun fetchFilms(): Single<List<Film>>

    @Insert(onConflict = REPLACE)
    fun retain(films: List<Film>): Completable

}
