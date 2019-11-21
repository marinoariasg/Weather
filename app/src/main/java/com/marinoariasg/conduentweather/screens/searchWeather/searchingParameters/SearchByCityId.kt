package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import android.text.InputType
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.Search
import timber.log.Timber

class SearchByCityId(var cityId: String = "", units: String, override val id: Int) : Search(units) {

    override val firstParameterHint
        get() = "City Id"
    override val firstParameterInputType: Int
        get() = InputType.TYPE_CLASS_NUMBER

    // Second parameter is not use
    override val secondParameterHint: String
        get() = ""
    override val secondParameterInputType: Int
        get() = InputType.TYPE_CLASS_TEXT

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        Timber.i("Get data searching by city Id")
        return repository.weatherByCityId(cityId = cityId, units = units)
    }

}