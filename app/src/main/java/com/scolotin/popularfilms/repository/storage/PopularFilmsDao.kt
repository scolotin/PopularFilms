package com.scolotin.popularfilms.repository.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.scolotin.popularfilms.model.Film
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PopularFilmsDao {

    @Query("SELECT * FROM films")
    fun fetchFilms(): Single<List<Film>>

    @Insert(onConflict = REPLACE)
    fun retain(films: List<Film>): Completable

}
