package com.marinoariasg.conduentweather.screens.currentLocation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CurrentLocationModelFactory(private val weatherLookUpParameters: WeatherLookUpParameters) :
    ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CurrentLocationViewModel::class.java)) {
            return CurrentLocationViewModel(weatherLookUpParameters) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}