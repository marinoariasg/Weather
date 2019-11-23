package com.marinoariasg.conduentweather.screens.searchWeather

import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*

class ParametersManager(units: String) {
    val byCityName: SearchByCityName = SearchByCityName(units = units, id = 1)
    val byCityId: SearchByCityId = SearchByCityId(units = units, id = 2)
    val byLatAndLon: SearchByLatAndLon = SearchByLatAndLon(units = units, id = 3)
    val byZipCode: SearchByZipCode = SearchByZipCode(units = units, id = 4)

    // Start with default searchParameter to display (byCityName)
    private var enabledParameter: Search = byCityName

    // Adds the text for the editText to the parameter that is currently on display
    fun getInputText(firstInput: String, secondInput: String) {
        when (enabledParameter) {
            is SearchByCityName -> {
                byCityName.cityName = firstInput
                byCityName.countryCode = secondInput
            }
            is SearchByCityId -> {
                byCityId.cityId = firstInput
            }
            is SearchByLatAndLon -> {
                byLatAndLon.latitude = firstInput
                byLatAndLon.longitude = secondInput
            }
            is SearchByZipCode -> {
                byZipCode.zipCode = firstInput
                byZipCode.countryCode = secondInput
            }
        }
    }

    fun enableFromId(SearchParameterId: Int): Search {
        when (SearchParameterId) {
            byCityName.id -> enable(byCityName)
            byCityId.id -> enable(byCityId)
            byLatAndLon.id -> enable(byLatAndLon)
            byZipCode.id -> enable(byZipCode)
        }
        return getEnabledParameter()
    }

    private fun enable(searchParameter: Search) {
        enabledParameter = searchParameter
    }

    fun getEnabledParameter() = enabledParameter
}