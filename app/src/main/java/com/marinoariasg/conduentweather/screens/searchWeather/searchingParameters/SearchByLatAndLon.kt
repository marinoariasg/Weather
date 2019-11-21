package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import android.text.InputType
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository

class SearchByLatAndLon(
    var latitude: String = "", var longitude: String = "", units: String, override val id: Int
) : Search(units) {

    override val firstParameterHint
        get() = "Latitude"
    override val firstParameterInputType: Int
        get() = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED

    override val secondParameterHint: String
        get() = "Longitude"
    override val secondParameterInputType: Int
        get() = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        return repository.weatherByLatAndLon(
            lat = latitude, lon = longitude, units = units
        )
    }

}
