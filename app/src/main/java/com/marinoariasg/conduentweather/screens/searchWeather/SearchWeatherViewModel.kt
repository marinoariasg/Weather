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

    fun onRadioButtonClicked(radioButtonId: Int) {
        when (radioButtonId) {
            1 -> {
                setVisible(searchingParameters.byCityName)
                _obj.value = searchingParameters.byCityName
            }

            2 -> {
                setVisible(searchingParameters.byCityId)
                _obj.value = searchingParameters.byCityId
            }
            3 -> setVisible(searchingParameters.byLatAndLon)
            4 -> setVisible(searchingParameters.byZipCode)
        }
    }

    private var _obj = MutableLiveData<Search>()
    val obj: LiveData<Search>
        get() = _obj

    private fun setVisible(searchObject: Search) {
        setSearchButtonVisible()
        searchingParameters.setVisibility(searchObject)
    }

    private fun setSearchButtonVisible() {
        // Just do this if the button is not visible.
        if (buttonVisibility.value == GONE) _buttonVisibility.value = VISIBLE
    }

    // Button can only call this when is visible (after a radio button have been selected)
    fun onButtonSearchClicked() {
        updateWeatherResponse(searchingParameters.getCurrentVisibleObject()!!)
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