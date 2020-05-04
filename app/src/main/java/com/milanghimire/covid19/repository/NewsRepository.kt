package com.milanghimire.covid19.repository

import com.milanghimire.covid19.api.RetrofitNewsInstance

class NewsRepository() {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitNewsInstance.api.getBreakingNews(countryCode, pageNumber)
}