package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository

abstract class Search(var units: String) {

    abstract val id: Int
    abstract val firstParameterHint: String
    abstract val firstParameterInputType: Int
    abstract val secondParameterHint: String
    abstract val secondParameterInputType: Int

    abstract suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData
}