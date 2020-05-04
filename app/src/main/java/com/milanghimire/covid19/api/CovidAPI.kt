package com.milanghimire.covid19.api

import com.milanghimire.covid19.models.CovidCountryResponse
import com.milanghimire.covid19.models.CovidWorldStatusResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CovidAPI {

//    https://documenter.getpostman.com/view/11144369/Szf6Z9B3?version=latest

    @GET("v2/countries/{countryName}")
    suspend fun getStatusByCountry(
        @Path("countryName")
        countryName: String = "nepal"
    ): Response<CovidCountryResponse>

    @GET("v2/all")
    suspend fun getStatusOfWorld(): Response<CovidWorldStatusResponse>
}