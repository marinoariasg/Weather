package com.marinoariasg.conduentweather.screens.searchWeather

import com.marinoariasg.conduentweather.screens.searchWeather.searchingParameters.*
import timber.log.Timber

class SearchingParameters(units: String) {
    val byCityName: SearchByCityName = SearchByCityName(units = units)
    val byCityId: SearchByCityId = SearchByCityId(units = units)
    val byLatAndLon: SearchByLatAndLon = SearchByLatAndLon(units = units)
    val byZipCode: SearchByZipCode = SearchByZipCode(units = units)
    private val searchingObjects = arrayListOf(byCityName, byCityId, byLatAndLon, byZipCode)

    fun setVisibility(searchObject: Search) {
        if (searchingObjects.contains(searchObject)) {
            for (objectToSetVisibility in searchingObjects) {
                if (objectToSetVisibility == searchObject) {
                    objectToSetVisibility.visible()
                    Timber.i("Parameter visible: $objectToSetVisibility")
                } else objectToSetVisibility.gone()
            }
        } else {
            Timber.i("search parameter not found: $searchObject")
            Timber.i("Add parameter to: $searchingObjects")
        }
    }



}