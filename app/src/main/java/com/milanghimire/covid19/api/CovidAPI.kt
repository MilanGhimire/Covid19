package com.milanghimire.covid19.api

import com.milanghimire.covid19.db.models.CovidCountryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidAPI {

    @GET("/v2/nepal...")
    suspend fun getStatusByCountry(
        @Query("country")
        countryName: String = "nepal"
    ): Response<CovidCountryResponse>
}