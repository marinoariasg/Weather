package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import android.text.InputType
import android.view.View
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.Search
import timber.log.Timber

class SearchByZipCode(
    var zipCode: String = "", var countryCode: String = "", units: String, override val id: Int
) : Search(units) {

    override val firstInputHint
        get() = "City ZipCode"
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
        return repository.weatherByZipCode(
            zipCode = zipCode, countryCode = countryCode, units = units
        )
    }
}