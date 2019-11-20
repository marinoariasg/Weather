package com.marinoariasg.conduentweather.screens.searchWeather


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.Search
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

const val GONE: Int = 8
const val VISIBLE: Int = 0

class SearchWeatherViewModel(_unitsFormat: String = "metric", application: Application) :
    AndroidViewModel(application) {

    private val _weatherResponse = MutableLiveData<WeatherData>()
    // The external immutable LiveData, The xml binding is observing this val
    val weatherResponse: LiveData<WeatherData>
        get() = _weatherResponse

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val weatherRepository: WeatherRepository = WeatherRepository()

    val searchingParameters = SearchingParameters(_unitsFormat)

    // Button Visibility and options
    private var _buttonVisibility = MutableLiveData<Int>(GONE)
    // Used by the xml
    val buttonVisibility: LiveData<Int>
        get() = _buttonVisibility

    private fun setSearchButtonVisible() {
        // Just do this if the button is not visible.
        if (buttonVisibility.value == GONE) _buttonVisibility.value = VISIBLE
    }

    /** Radio buttons options **/
    fun onSetByCityName() {
        searchingParameters.setVisibility(searchingParameters.byCityName)
        setSearchButtonVisible()
    }

    fun onSetByCityId() {
        searchingParameters.setVisibility(searchingParameters.byCityId)
        setSearchButtonVisible()
    }

    fun onSetByLatAndLon() {
        searchingParameters.setVisibility(searchingParameters.byLatAndLon)
        setSearchButtonVisible()
    }

    fun onSetByZipCode() {
        searchingParameters.setVisibility(searchingParameters.byZipCode)
        setSearchButtonVisible()
    }

    // Button can only call this when is visible (after a radio button have been selected)
    // Use by the button on the xml
    fun onSearch() {
        when (VISIBLE) {
            searchingParameters.byCityName.visibility.value -> updateWeatherResponse(
                searchingParameters.byCityName
            )
            searchingParameters.byCityId.visibility.value -> updateWeatherResponse(
                searchingParameters.byCityId
            )
            searchingParameters.byLatAndLon.visibility.value -> updateWeatherResponse(
                searchingParameters.byLatAndLon
            )
            searchingParameters.byZipCode.visibility.value -> updateWeatherResponse(
                searchingParameters.byZipCode
            )
        }
    }

    private fun updateWeatherResponse(searchObject: Search) {
        viewModelScope.launch {
            _weatherResponse.value = searchObject.getDataFromRepository(weatherRepository)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}