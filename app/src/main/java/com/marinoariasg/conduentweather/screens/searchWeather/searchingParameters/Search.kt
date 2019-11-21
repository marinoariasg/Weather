package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository

abstract class Search(var units: String) {

    abstract suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData
}