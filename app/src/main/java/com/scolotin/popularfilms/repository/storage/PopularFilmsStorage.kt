package com.scolotin.popularfilms.repository.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.scolotin.popularfilms.model.Film

@Database(exportSchema = false, entities = [Film::class], version = 1)
abstract class PopularFilmsStorage : RoomDatabase() {

    abstract fun getPopularFilmsDao(): PopularFilmsDao

}
