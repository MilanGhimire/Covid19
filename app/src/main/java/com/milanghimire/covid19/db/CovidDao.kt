package com.milanghimire.covid19.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.milanghimire.covid19.entities.CovidCountry

@Dao
interface CovidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(covid: CovidCountry): Long

    @Delete
    suspend fun deleteCovid(covid: CovidCountry)

    @Query("SELECT * FROM covids")
    fun getAllCovids(): LiveData<List<CovidCountry>>
}