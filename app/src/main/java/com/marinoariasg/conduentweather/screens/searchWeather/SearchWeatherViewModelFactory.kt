package com.marinoariasg.conduentweather.screens.searchWeather

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SearchWeatherViewModelFactory(
    private val unitsFormat: String, private val application: Application
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchWeatherViewModel::class.java)) {
            return SearchWeatherViewModel(
                unitsFormat,
                application
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}