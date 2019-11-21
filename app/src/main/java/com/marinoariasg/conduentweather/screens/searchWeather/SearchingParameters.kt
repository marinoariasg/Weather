package com.marinoariasg.conduentweather.screens.searchWeather

import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*

class SearchingParameters(units: String) {
    val byCityName: SearchByCityName = SearchByCityName(units = units)
    val byCityId: SearchByCityId = SearchByCityId(units = units)
    val byLatAndLon: SearchByLatAndLon = SearchByLatAndLon(units = units)
    val byZipCode: SearchByZipCode = SearchByZipCode(units = units)

    fun addInfoFromEtToShowingParameter(
        firstInput: String, secondInput: String, showingSearchParameter: Search
    ) {
        when (showingSearchParameter) {
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

}