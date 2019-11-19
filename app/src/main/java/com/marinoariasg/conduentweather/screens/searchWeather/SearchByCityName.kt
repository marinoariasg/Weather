package com.marinoariasg.conduentweather.screens.searchWeather

import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import timber.log.Timber


class SearchByCityName(
    var cityName: String = "", var countryCode: String = "", units: String) : Search(units) {

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        Timber.i("Get data searching by city name and Country code")
        return repository.weatherByCityName(
            cityName = cityName, countryCode = countryCode, units = units
        )
    }

}
