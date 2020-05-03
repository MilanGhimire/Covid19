package com.milanghimire.covid19.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.milanghimire.covid19.db.models.CovidCountryResponse

@Dao
interface CovidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(covid: CovidCountryResponse): Long

    @Delete
    suspend fun deleteCovid(covid: CovidCountryResponse)

    @Query("SELECT * FROM covids")
    fun getAllCovids(): LiveData<List<CovidCountryResponse>>
}