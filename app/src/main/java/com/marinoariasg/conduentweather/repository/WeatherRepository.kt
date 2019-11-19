package com.marinoariasg.conduentweather.repository

import com.marinoariasg.conduentweather.network.OpenWeatherApi
import com.marinoariasg.conduentweather.network.WeatherData
import kotlinx.coroutines.Deferred
import timber.log.Timber

class WeatherRepository {

    suspend fun weatherByCityName(cityName: String, countryCode: String, units: String? = null)
            : WeatherData {

        // Enforce this: q={city name},{country code} for weather api
        val cityNameAndCountryCode = addParametersWithComa(cityName, countryCode)

        val getByCityName = OpenWeatherApi.retrofitService.getByCityName(
            queryFilter = cityNameAndCountryCode, units = units
        )
        return tryToGetWeatherData(getByCityName)
    }

    suspend fun weatherByCityId(cityId: String, units: String? = null): WeatherData {
        val getByCityId = OpenWeatherApi.retrofitService.getByCityId(
            cityId = cityId, units = units
        )
        return tryToGetWeatherData(getByCityId)
    }

    suspend fun weatherByLatAndLon(lat: String, lon: String, units: String? = null): WeatherData {
        val getCityByLatAndLon = OpenWeatherApi.retrofitService
            .getByGeographicCoordinates(latitude = lat, longitude = lon, units = units)
        return tryToGetWeatherData(getCityByLatAndLon)
    }

    suspend fun weatherByZipCode(zipCode: String, countryCode: String, units: String? = null):
            WeatherData {

        // Enforce this: zip={zip code},{country code} for weather api
        val zipCodeAndCountryCode = addParametersWithComa(zipCode, countryCode)

        val getCityByZipCode = OpenWeatherApi.retrofitService.getByZipCode(
            zipCode = zipCodeAndCountryCode, units = units)
        return tryToGetWeatherData(getCityByZipCode)
    }

    // Second parameter can be empty
    private fun addParametersWithComa(firstParameter: String, secondParameter: String): String {
        return when (secondParameter) {
            "" -> firstParameter
            else -> "${firstParameter},${secondParameter}"
        }
    }

    private suspend fun tryToGetWeatherData(deferredWeatherData: Deferred<WeatherData>): WeatherData {
        // Initialize with default values
        var result = WeatherData()
        try {
            result = deferredWeatherData.await()
            Timber.i("$result")
        } catch (t: Throwable) {
            Timber.e("Failure: ${t.message}")
        }
        return result
    }
}