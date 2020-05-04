package com.milanghimire.covid19.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "covids"
)
data class CovidCountry(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val cases: Int,
    val todayCases: Int,
    val deaths: Int,
    val todayDeaths: Int,
    val recovered: Int,
    val active: Int,
    val critical: Int,
    val tests: Int
)