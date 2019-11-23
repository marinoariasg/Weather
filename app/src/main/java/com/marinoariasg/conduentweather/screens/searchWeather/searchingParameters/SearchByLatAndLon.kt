package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import android.text.InputType
import android.view.View
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import timber.log.Timber

class SearchByLatAndLon(
    var latitude: String = "", var longitude: String = "", units: String, override val id: Int
) : Search(units) {

    override val firstInputHint
        get() = "Latitude"
    override val firstInputType: Int
        get() = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
    override val firstInputVisibility: Int
        get() = View.VISIBLE

    override val secondInputHint: String
        get() = "Longitude"
    override val secondInputInputType: Int
        get() = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED
    override val secondInputVisibility: Int
        get() = View.VISIBLE

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        Timber.i("Call repository")
        return repository.weatherByLatAndLon(lat = latitude, lon = longitude, units = units)
    }

}
