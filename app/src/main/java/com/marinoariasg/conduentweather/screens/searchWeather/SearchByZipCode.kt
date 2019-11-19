package com.marinoariasg.conduentweather.screens.searchWeather

import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository

class SearchByZipCode(
    var zipCode: String = "", var countryCode: String = "", units: String
) : Search(units) {

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        return repository.weatherByZipCode(
            zipCode = zipCode, countryCode = countryCode, units = units
        )
    }
}