package com.marinoariasg.conduentweather.screens.searchWeather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchWeatherViewModel(_unitsFormat: String = "imperial", application: Application) :
    AndroidViewModel(application) {

    private val _weatherResponse = MutableLiveData<WeatherData>()
    // The external immutable LiveData, The xml binding is observing this val
    val weatherResponse: LiveData<WeatherData>
        get() = _weatherResponse

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val weatherRepository: WeatherRepository = WeatherRepository()

    val searchingParameters = SearchingParameters(_unitsFormat)

    // Start with default searchParameter to display (byCityName)
    private var _searchParameterToShow = MutableLiveData<Search>(searchingParameters.byCityName)
    // Used by TextView on the xml
    val searchParameterToShow: LiveData<Search>
        get() = _searchParameterToShow

    fun onRadioButtonClicked(searchParameterId: Int) {
        when (searchParameterId) {
            searchingParameters.byCityName.id -> setVisible(searchingParameters.byCityName)
            searchingParameters.byCityId.id -> setVisible(searchingParameters.byCityId)
            searchingParameters.byLatAndLon.id -> setVisible(searchingParameters.byLatAndLon)
            searchingParameters.byZipCode.id -> setVisible(searchingParameters.byZipCode)
        }
    }

    private fun setVisible(searchParameter: Search) {
        _searchParameterToShow.value = searchParameter
    }

    fun onButtonSearchClicked() {
        // Before checking for weather get the editText info from user
        searchingParameters.getTextFromEditText(
            showingSearchParameter = searchParameterToShow.value!!
        )
        // Update with the current parameter selected by the radio button
        updateWeatherResponse(searchParameterToShow.value!!)
    }

    private fun updateWeatherResponse(searchParameter: Search) {
        viewModelScope.launch {
            _weatherResponse.value = searchParameter.getDataFromRepository(weatherRepository)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}