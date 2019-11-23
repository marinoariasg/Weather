package com.marinoariasg.conduentweather.screens.searchWeather

import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*

class ParametersManager(units: String) {
    private val byCityName: SearchByCityName = SearchByCityName(units = units)
    private val byCityId: SearchByCityId = SearchByCityId(units = units)
    private val byLatAndLon: SearchByLatAndLon = SearchByLatAndLon(units = units)
    private val byZipCode: SearchByZipCode = SearchByZipCode(units = units)

    // Start with default searchParameter to display (byCityName)
    private var enabledParameter: Search = byCityName

    // Add the text for the editText to the parameter that is currently on display
    fun getInputText(firstInput: String, secondInput: String) {
        when (getEnabled()) {
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

    fun enableWithId(SearchParameterId: Id): Search {
        when (SearchParameterId) {
            byCityId.id -> enable(byCityId)
            byLatAndLon.id -> enable(byLatAndLon)
            byZipCode.id -> enable(byZipCode)
            else -> enable(byCityName)
        }
        return getEnabled()
    }

    private fun enable(searchParameter: Search) {
        enabledParameter = searchParameter
    }

    fun getEnabled() = enabledParameter
}