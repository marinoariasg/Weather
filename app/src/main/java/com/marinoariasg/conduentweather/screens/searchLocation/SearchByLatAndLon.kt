package com.marinoariasg.conduentweather.screens.searchLocation

import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository

class SearchByLatAndLon(
    var latitude: String = "", var longitude: String = "", units: String
) : Search(units) {

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        return repository.weatherByLatAndLon(
            lat = latitude.toDouble(), lon = longitude.toDouble(), units = units
        )
    }

}
