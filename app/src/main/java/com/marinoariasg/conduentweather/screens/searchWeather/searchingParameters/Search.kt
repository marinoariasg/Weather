package com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters

import com.marinoariasg.conduentweather.network.WeatherData
import com.marinoariasg.conduentweather.repository.WeatherRepository

abstract class Search(var units: String) {

    abstract val id: Id

    abstract val firstInputHint: String
    abstract val firstInputType: Int
    abstract val firstInputVisibility: Int

    abstract val secondInputHint: String
    abstract val secondInputInputType: Int
    abstract val secondInputVisibility: Int

    abstract suspend fun getDataFromRepository(repository: WeatherRepository): WeatherData

    // TODO: Try to get a different return value, something like that tells
    //  positive output yes it can be update
    //  negative output not this can not be done because of this:
    abstract fun canUpdateTextInputs(firstInput: String, secondInput: String): String?
}