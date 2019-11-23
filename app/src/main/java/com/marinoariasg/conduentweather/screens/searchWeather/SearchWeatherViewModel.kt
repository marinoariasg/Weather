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

    private var viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _weatherResponse = MutableLiveData<WeatherData>()
    // The external immutable LiveData, The xml binding is observing this val
    val weatherResponse: LiveData<WeatherData>
        get() = _weatherResponse

    private val weatherRepository: WeatherRepository = WeatherRepository()

    val parametersManager = ParametersManager(_unitsFormat)

    // EditTexts inputs from the xml
    var editTextFirstInput = ""
    var editTextSecondInput = ""

    // Start with default input so it shows something at initialization
    private var _parameterEnabled =
        MutableLiveData<Search>(parametersManager.getParameterEnabled())
    // The external immutable LiveData, The EditText in xml binding is observing this val
    val parameterEnabled: LiveData<Search>
        get() = _parameterEnabled

    fun onRadioButtonClicked(searchParameterId: Int) {
        // Update EditText with new parameter selected by user
        _parameterEnabled.value = parametersManager.enableFromId(searchParameterId)
    }

    fun onButtonSearchClicked() {
        // Before checking for weather get the editText input from user
        parametersManager.getInputText(
            firstInput = editTextFirstInput, secondInput = editTextSecondInput
        )
        // Update with the current parameter selected by the radio button
        updateWeatherResponse(parametersManager.getParameterEnabled())
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