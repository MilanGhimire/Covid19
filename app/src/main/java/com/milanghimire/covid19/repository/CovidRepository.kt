package com.milanghimire.covid19.repository

import com.milanghimire.covid19.api.RetrofitCovidInstance
import com.milanghimire.covid19.db.CovidDatabase

class CovidRepository(
    val db: CovidDatabase
) {
    suspend fun getCovidStatus(countryName: String) =
        RetrofitCovidInstance.api.getStatusByCountry(countryName)
}