package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import android.text.InputType
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import timber.log.Timber

class SearchByCityName(
    var cityName: String = "", var countryCode: String = "", units: String, override val id: Int
) : Search(units) {

    override val firstParameterHint
        get() = "City Name"
    override val firstParameterInputType: Int
        get() = InputType.TYPE_CLASS_TEXT

    override val secondParameterHint: String
        get() = "Country Code"
    override val secondParameterInputType: Int
        get() = InputType.TYPE_CLASS_TEXT

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        Timber.i("Get data searching by city name and Country code")
        return repository.weatherByCityName(
            cityName = cityName, countryCode = countryCode, units = units
        )
    }

}
