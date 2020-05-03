package com.milanghimire.covid19.repository

import com.milanghimire.covid19.api.RetrofitInstance
import com.milanghimire.covid19.db.CovidDatabase

class CovidRepository(
    val db: CovidDatabase
) {
    suspend fun getCovidStatus(countryName: String) =
        RetrofitInstance.api.getStatusByCountry(countryName)
}