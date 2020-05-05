package com.milanghimire.covid19.api

import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.milanghimire.covid19.util.Constants.Companion.NEWS_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

//    /v2/top-headlines?country=us&category=business&apiKey=b2d9cfdde84943b6aa901e91383f5533
    @GET("v2/top-headlines")
//    used suspend for using coroutines
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = NEWS_API_KEY
    ): Response<NewsResponse>

//    http://newsapi.org/v2/everything?q=bitcoin&from=2020-04-05&sortBy=publishedAt&apiKey=b2d9cfdde84943b6aa901e91383f5533
    @GET("v2/everything")
//    used suspend for using coroutines
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = NEWS_API_KEY
    ): Response<NewsResponse>
}