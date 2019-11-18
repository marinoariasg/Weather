package com.marinoariasg.conduentweather.currentLocation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CurrentLocationModelFactory(private val simpleWeatherData: SimpleWeatherData) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrentLocationViewModel::class.java)) {
            return CurrentLocationViewModel(simpleWeatherData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}