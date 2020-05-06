package com.milanghimire.covid19.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androiddevs.mvvmnewsapp.models.NewsResponse
import com.milanghimire.covid19.repository.NewsRepository
import com.milanghimire.covid19.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    val breakingNewsPage = 1

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        try {
            val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
            breakingNews.postValue(handleBreakingNewsResponse(response))
        } catch (ex: UnknownHostException) {
            breakingNews.postValue(Resource.Error("No internet connection."))
        } catch (ex: SocketTimeoutException) {
            breakingNews.postValue(Resource.Error("Server is not responding."))
        } catch (ex: Exception) {
            breakingNews.postValue(Resource.Error("Unknown error found."))
        }
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}