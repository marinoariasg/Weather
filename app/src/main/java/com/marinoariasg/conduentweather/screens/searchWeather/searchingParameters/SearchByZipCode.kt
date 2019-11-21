package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import android.text.InputType
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.Search

class SearchByZipCode(
    var zipCode: String = "", var countryCode: String = "", units: String, override val id: Int
) : Search(units) {

    override val firstParameterHint
        get() = "City ZipCode"
    override val firstParameterInputType: Int
        get() = InputType.TYPE_CLASS_TEXT

    override val secondParameterHint: String
        get() = "Country Code"
    override val secondParameterInputType: Int
        get() = InputType.TYPE_CLASS_TEXT

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        return repository.weatherByZipCode(
            zipCode = zipCode, countryCode = countryCode, units = units
        )
    }
}