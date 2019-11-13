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

// TODO: add private val _unitsFormat: String to the constructor
class CurrentLocationViewModel(private val _currentLocationData: CurrentLocationData) :
    ViewModel() {

    val currentLocationData: CurrentLocationData
        get() = _currentLocationData

    private val _response = MutableLiveData<WeatherData>()

    // The external immutable LiveData, The xml binding is observing this val
    val response: LiveData<WeatherData>
        get() = _response

    private var vieModelJob = Job()
    private val viewModelScope = CoroutineScope(vieModelJob + Dispatchers.Main)

    private val weatherRepository: WeatherRepository = WeatherRepository()

    /**
     * Call getWeatherData() on init so we can display status immediately.
     */
    init {
        viewModelScope.launch {
            _response.value = weatherRepository.refreshWeatherData(
                cityName = _currentLocationData.cityName,
                countryCode = _currentLocationData.countryCode,
                units = "metric"
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        vieModelJob.cancel()
    }
}