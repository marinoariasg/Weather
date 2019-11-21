package com.marinoariasg.conduentweather.screens.searchWeather

import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*

class SearchingParameters(units: String) {
    val byCityName: SearchByCityName = SearchByCityName(units = units, id = 1)
    val byCityId: SearchByCityId = SearchByCityId(units = units, id = 2)
    val byLatAndLon: SearchByLatAndLon = SearchByLatAndLon(units = units, id = 3)
    val byZipCode: SearchByZipCode = SearchByZipCode(units = units, id = 4)

    // EditTexts inputs from the xml
    var editTextFirstInput = ""
    var editTextSecondInput = ""

    // Adds the text for the editText to the parameter that is currently on display
    fun getTextFromEditText(showingSearchParameter: Search) {
        when (showingSearchParameter) {
            is SearchByCityName -> {
                byCityName.cityName = editTextFirstInput
                byCityName.countryCode = editTextSecondInput
            }
            is SearchByCityId -> {
                byCityId.cityId = editTextFirstInput
            }
            is SearchByLatAndLon -> {
                byLatAndLon.latitude = editTextFirstInput
                byLatAndLon.longitude = editTextSecondInput
            }
            is SearchByZipCode -> {
                byZipCode.zipCode = editTextFirstInput
                byZipCode.countryCode = editTextSecondInput
            }
        }
    }

}