package com.marinoariasg.conduentweather.screens.searchWeather

import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*

class ParametersManager(units: String) {
    private val byCityName: SearchByCityName = SearchByCityName(units = units)
    private val byCityId: SearchByCityId = SearchByCityId(units = units)
    private val byLatAndLon: SearchByLatAndLon = SearchByLatAndLon(units = units)
    private val byZipCode: SearchByZipCode = SearchByZipCode(units = units)

    // Start with default searchParameter to display (byCityName)
    private var enabledParameter: Search = byCityName

    // Add the text from the editText to the parameter that is currently on display
    fun isTextInputOk(firstInput: String, secondInput: String): String? {
        return when (getEnabled()) {
            is SearchByCityName -> {
                byCityName.canUpdateTextInputs(firstInput = firstInput, secondInput = secondInput)
            }
            is SearchByCityId -> {
                byCityId.canUpdateTextInputs(firstInput = firstInput, secondInput = secondInput)
            }
            is SearchByLatAndLon -> {
                byLatAndLon.canUpdateTextInputs(firstInput = firstInput, secondInput = secondInput)
            }
            is SearchByZipCode -> {
                byZipCode.canUpdateTextInputs(firstInput = firstInput, secondInput = secondInput)
            }
            else -> "No parameter enabled"
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