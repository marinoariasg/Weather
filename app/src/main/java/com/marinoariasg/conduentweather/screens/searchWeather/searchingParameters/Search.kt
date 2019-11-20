package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import com.marinoariasg.conduentweather.screens.searchWeather.GONE
import com.marinoariasg.conduentweather.screens.searchWeather.VISIBLE

abstract class Search(var units: String) {

    private var _visibility = MutableLiveData<Int>(GONE)

    // Used by the xml with biding data
    val visibility: LiveData<Int>
        get() = _visibility

    fun visible() {
        _visibility.value = VISIBLE
    }

    fun gone() {
        _visibility.value = GONE
    }

    abstract suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData
}