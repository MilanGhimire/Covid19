package com.milanghimire.covid19.db.models

data class CovidCountryResponse(
    val active: Int,
    val cases: Int,
    val continent: String,
    val country: String,
    val countryInfo: CountryInfo,
    val critical: Int,
    val deaths: Int,
    val recovered: Int,
    val tests: Int,
    val todayCases: Int,
    val todayDeaths: Int,
    val updated: Long
) {
}