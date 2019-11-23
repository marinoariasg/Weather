package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import android.text.InputType
import android.view.View
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import timber.log.Timber

class SearchByCityName(
    var cityName: String = "", var countryCode: String = "", units: String) : Search(units) {

    override val id: Id
        get() = Id.CITY_NAME

    override val firstInputHint
        get() = "City Name"
    override val firstInputType: Int
        get() = InputType.TYPE_CLASS_TEXT
    override val firstInputVisibility: Int
        get() = View.VISIBLE

    override val secondInputHint: String
        get() = "Country Code"
    override val secondInputInputType: Int
        get() = InputType.TYPE_CLASS_TEXT
    override val secondInputVisibility: Int
        get() = View.VISIBLE

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        Timber.i("Call repository")
        return repository.weatherByCityName(
            cityName = cityName, countryCode = countryCode, units = units
        )
    }

}
