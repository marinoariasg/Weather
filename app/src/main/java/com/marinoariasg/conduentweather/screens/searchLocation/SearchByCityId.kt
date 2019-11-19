package com.marinoariasg.conduentweather.screens.searchLocation

import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import timber.log.Timber

class SearchByCityId(var cityId: String = "", units: String) : Search(units) {

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        Timber.i("Get data searching by city Id")
        return repository.weatherByCityId(cityId = cityId, units = units)
    }

}