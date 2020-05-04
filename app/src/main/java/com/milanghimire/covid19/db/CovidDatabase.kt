package com.milanghimire.covid19.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.milanghimire.covid19.db.entities.CovidCountry

@Database(
    entities = [CovidCountry::class],
    version = 1
)
abstract class CovidDatabase : RoomDatabase() {

    abstract fun getCovidDao(): CovidDao

    companion object {
        @Volatile
        private var instance: CovidDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CovidDatabase::class.java,
                "covids_db.db"
            ).build()
    }
}