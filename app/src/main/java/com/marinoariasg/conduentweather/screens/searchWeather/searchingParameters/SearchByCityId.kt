package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import android.text.InputType
import android.view.View
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.Search
import timber.log.Timber

class SearchByCityId(var cityId: String = "", units: String) : Search(units) {

    override val id: Id
        get() = Id.CITY_ID

    override val firstInputHint
        get() = "City Id"
    override val firstInputType: Int
        get() = InputType.TYPE_CLASS_NUMBER
    override val firstInputVisibility: Int
        get() = View.VISIBLE

    // Second parameter is not in use
    override val secondInputHint: String
        get() = ""
    override val secondInputInputType: Int
        get() = InputType.TYPE_CLASS_TEXT
    override val secondInputVisibility: Int
        get() = View.GONE

    override suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData {
        Timber.i("Call repository")
        return repository.weatherByCityId(cityId = cityId, units = units)
    }

}