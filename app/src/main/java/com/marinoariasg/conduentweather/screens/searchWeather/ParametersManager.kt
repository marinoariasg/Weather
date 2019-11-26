package com.marinoariasg.conduentweather.screens.searchWeather

import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*

class ParametersManager(units: String) {
    private val byCityName = Search.ByCityName(units = units)
    private val byCityId = Search.ByCityId(units = units)
    private val byLatAndLon = Search.ByLatAndLon(units = units)
    private val byZipCode = Search.ByZipCode(units = units)

    // Start with default searchParameter to display (byCityName)
    private var enabledParameter: Search = byCityName

    // Add the text from the editText to the parameter that is currently on display
    fun isTextInputOk(firstInput: String, secondInput: String): String? {
        return when (getEnabled()) {
            is Search.ByCityName -> {
                byCityName.canUpdateTextInputs(firstInput = firstInput, secondInput = secondInput)
            }
            is Search.ByCityId -> {
                byCityId.canUpdateTextInputs(firstInput = firstInput, secondInput = secondInput)
            }
            is Search.ByLatAndLon -> {
                byLatAndLon.canUpdateTextInputs(firstInput = firstInput, secondInput = secondInput)
            }
            is Search.ByZipCode -> {
                byZipCode.canUpdateTextInputs(firstInput = firstInput, secondInput = secondInput)
            }
        }
    }

    fun enableWithId(searchParameterId: ParameterId): Search {
        when (searchParameterId) {
            byCityId.parameterId -> enable(byCityId)
            byLatAndLon.parameterId -> enable(byLatAndLon)
            byZipCode.parameterId -> enable(byZipCode)
            else -> enable(byCityName)
        }
        return getEnabled()
    }

    private fun enable(searchParameter: Search) {
        enabledParameter = searchParameter
    }

    fun getEnabled() = enabledParameter
}