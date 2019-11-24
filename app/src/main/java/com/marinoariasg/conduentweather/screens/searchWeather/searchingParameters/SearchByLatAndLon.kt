package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import android.text.InputType
import android.view.View
import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository
import timber.log.Timber

class SearchByLatAndLon(units: String) : Search(units) {

    private var latitude: String = ""

    private var longitude: String = ""

    override val id: Id
        get() = Id.LAT_LON

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

    // TODO: Fix validations for all possible conditions.
    override fun canUpdateTextInputs(firstInput: String, secondInput: String): String? {
        return if (firstInput.isNotEmpty()) {
            latitude = firstInput
            longitude = secondInput
            null
        } else {
            "e.g: lat= 4.72069, lon= -73.96926"
        }
    }

}
