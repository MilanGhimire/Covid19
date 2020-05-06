package com.milanghimire.covid19.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milanghimire.covid19.models.CovidCountryResponse
import com.milanghimire.covid19.models.CovidWorldStatusResponse
import com.milanghimire.covid19.repository.CovidRepository
import com.milanghimire.covid19.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CovidViewModel(
    val covidRepository: CovidRepository
) : ViewModel() {

    val covidStatus: MutableLiveData<Resource<CovidCountryResponse>> = MutableLiveData()
    val covidWorldStatus: MutableLiveData<Resource<CovidWorldStatusResponse>> = MutableLiveData()

    init {
        getCovidWorldStatus()
    }

    fun getCovidCountryStatus(countryName: String) = viewModelScope.launch {
        covidStatus.postValue(Resource.Loading())
        try {
            val response = covidRepository.getCovidStatus(countryName)
            Log.d("ViewModel", "Country status: ${response.body()}")
            covidStatus.postValue(handleCovidStatusResponse(response))
        } catch (ex: UnknownHostException) {
            covidStatus.postValue(Resource.Error("No internet connection."))
        } catch (ex: SocketTimeoutException) {
            covidStatus.postValue(Resource.Error("Server is not responding."))
        } catch (ex: Exception) {
            covidStatus.postValue(Resource.Error("Unknown error found."))
        }
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
        covidWorldStatus.postValue(Resource.Loading())
        try {
            val response = covidRepository.getCovidWorldStatus()
            Log.d("ViewModel", "World status: ${response.body()}")
            covidWorldStatus.postValue(handleCovidWorldStatusResponse(response))
        } catch (ex: UnknownHostException) {
            covidWorldStatus.postValue(Resource.Error("No internet connection."))
        } catch (ex: SocketTimeoutException) {
            covidWorldStatus.postValue(Resource.Error("Server is not responding."))
        } catch (ex: Exception) {
            covidWorldStatus.postValue(Resource.Error("Unknown error found."))
        }
    }

    private fun handleCovidWorldStatusResponse(response: Response<CovidWorldStatusResponse>) : Resource<CovidWorldStatusResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}