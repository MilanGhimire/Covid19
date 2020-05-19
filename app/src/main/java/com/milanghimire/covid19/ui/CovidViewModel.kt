package com.milanghimire.covid19.ui

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milanghimire.covid19.CovidApplication
import com.milanghimire.covid19.models.CovidCountryResponse
import com.milanghimire.covid19.models.CovidWorldStatusResponse
import com.milanghimire.covid19.repository.CovidRepository
import com.milanghimire.covid19.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CovidViewModel(
    app: Application,
    val covidRepository: CovidRepository
) : AndroidViewModel(app) {

    val covidStatus: MutableLiveData<Resource<CovidCountryResponse>> = MutableLiveData()
    val covidWorldStatus: MutableLiveData<Resource<CovidWorldStatusResponse>> = MutableLiveData()

    init {
        getCovidWorldStatus()
    }

    fun getCovidCountryStatus(countryName: String) = viewModelScope.launch {
        safeCovidCountryStatusCall(countryName)
    }

    private fun handleCovidStatusResponse(response: Response<CovidCountryResponse>) : Resource<CovidCountryResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun getCovidWorldStatus() = viewModelScope.launch {
        safeCovidWorldStatusCall()
    }

    private fun handleCovidWorldStatusResponse(response: Response<CovidWorldStatusResponse>) : Resource<CovidWorldStatusResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private suspend fun safeCovidCountryStatusCall(countryName: String) {
        covidStatus.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = covidRepository.getCovidStatus(countryName)
                Log.d("ViewModel", "Country status: ${response.body()}")
                covidStatus.postValue(handleCovidStatusResponse(response))
            } else {
                covidStatus.postValue(Resource.Error("No internet connection."))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> covidStatus.postValue(Resource.Error("Network failure."))
                is SocketTimeoutException -> covidStatus.postValue(Resource.Error("Server is not responding."))
                else -> covidStatus.postValue(Resource.Error("Unknown network error found."))
            }
        }
    }

    private suspend fun safeCovidWorldStatusCall() {
        covidWorldStatus.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = covidRepository.getCovidWorldStatus()
                Log.d("ViewModel", "World status: ${response.body()}")
                covidWorldStatus.postValue(handleCovidWorldStatusResponse(response))
            } else {
                covidWorldStatus.postValue(Resource.Error("No internet connection."))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> covidWorldStatus.postValue(Resource.Error("Network failure."))
                is SocketTimeoutException -> covidWorldStatus.postValue(Resource.Error("Server is not responding."))
                else -> covidWorldStatus.postValue(Resource.Error("Unknown network error found."))
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<CovidApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}