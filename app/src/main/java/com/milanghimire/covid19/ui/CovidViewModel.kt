package com.milanghimire.covid19.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.milanghimire.covid19.db.models.CovidCountryResponse
import com.milanghimire.covid19.repository.CovidRepository
import com.milanghimire.covid19.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CovidViewModel(
    val covidRepository: CovidRepository
) : ViewModel() {

    val covidStatus: MutableLiveData<Resource<CovidCountryResponse>> = MutableLiveData()

    init {
        getCovidStatus("nepal")
    }

    fun getCovidStatus(countryName: String) = viewModelScope.launch {
        covidStatus.postValue(Resource.Loading())
        val response = covidRepository.getCovidStatus(countryName)
        covidStatus.postValue(handleCovidStatusResponse(response))
    }

    private fun handleCovidStatusResponse(response: Response<CovidCountryResponse>) : Resource<CovidCountryResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}