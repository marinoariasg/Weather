package com.marinoariasg.conduentweather.screens.searchWeather

import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*

class SearchingParameters(units: String) {
    val byCityName: SearchByCityName = SearchByCityName(units = units, id = 1)
    val byCityId: SearchByCityId = SearchByCityId(units = units, id = 2)
    val byLatAndLon: SearchByLatAndLon = SearchByLatAndLon(units = units, id = 3)
    val byZipCode: SearchByZipCode = SearchByZipCode(units = units, id = 4)

    // Start with default searchParameter to display (byCityName)
    private var visibleParameter: Search = byCityName

    // Adds the text for the editText to the parameter that is currently on display
    fun getInputText(firstInput: String, secondInput: String) {
        when (visibleParameter) {
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

    fun setVisibleFromId(SearchParameterId: Int): Search {
        when (SearchParameterId) {
            byCityName.id -> setVisible(byCityName)
            byCityId.id -> setVisible(byCityId)
            byLatAndLon.id -> setVisible(byLatAndLon)
            byZipCode.id -> setVisible(byZipCode)
        }
        return getVisibleParameter()
    }

    private fun setVisible(searchParameter: Search) {
        visibleParameter = searchParameter
    }

    fun getVisibleParameter() = visibleParameter
}