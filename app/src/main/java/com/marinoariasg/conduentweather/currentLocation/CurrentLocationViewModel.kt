package com.marinoariasg.conduentweather.currentLocation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CurrentLocationViewModel(var simpleWeatherData: SimpleWeatherData) :
    ViewModel() {

    private var _weatherData = MutableLiveData<WeatherData>()
    // The external immutable LiveData, The xml binding is observing this val
    val weatherData: LiveData<WeatherData>
        get() = _weatherData

    private var vieModelJob = Job()
    private val viewModelScope = CoroutineScope(vieModelJob + Dispatchers.Main)

    private val weatherRepository: WeatherRepository = WeatherRepository()

    /**
     * Call getSimpleWeatherData() on init so we can display status immediately.
     */
    init {
        getWeatherDataByCityName()
    }

    fun getWeatherDataByCityName() {
        viewModelScope.launch {
            _weatherData.value = weatherRepository.cityNameData(
                cityName = simpleWeatherData.cityName,
                countryCode = simpleWeatherData.countryCode,
                units = simpleWeatherData.units
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        vieModelJob.cancel()
    }
}