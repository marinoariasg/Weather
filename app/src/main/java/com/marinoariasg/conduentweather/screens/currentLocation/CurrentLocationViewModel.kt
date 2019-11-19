package com.marinoariasg.conduentweather.screens.currentLocation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marinoariasg.conduentweather.screens.WeatherLookUpParameters
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CurrentLocationViewModel(var weatherLookUpParameters: WeatherLookUpParameters) :
    ViewModel() {

    private var _weatherData = MutableLiveData<WeatherData>()
    // The external immutable LiveData, This contains all weather data coming from the
    // OpenWeather API, The xml binding is observing this property.
    val weatherData: LiveData<WeatherData>
        get() = _weatherData

    private var vieModelJob = Job()
    private val viewModelScope = CoroutineScope(vieModelJob + Dispatchers.Main)

    private val weatherRepository: WeatherRepository = WeatherRepository()

    fun getWeatherDataByCityName() {
        viewModelScope.launch {
            _weatherData.value = weatherRepository.cityNameData(
                cityName = weatherLookUpParameters.cityName,
                countryCode = weatherLookUpParameters.countryCode,
                units = weatherLookUpParameters.units
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        vieModelJob.cancel()
    }
}