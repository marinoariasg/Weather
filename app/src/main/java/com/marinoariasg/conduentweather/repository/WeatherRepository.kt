package com.marinoariasg.conduentweather.repository

import com.marinoariasg.conduentweather.network.OpenWeatherApi
import com.marinoariasg.conduentweather.network.WeatherData
import timber.log.Timber

class WeatherRepository() {

    suspend fun refreshWeatherData(cityName: String, countryCode: String, units: String? = null)
            : WeatherData {
        // Enforce this: q={city name},{country code} for weather api
        val cityNameAndCountryCode = when (countryCode) {
            "" -> cityName
            else -> "${cityName},${countryCode}"
        }

        var getByCityName = OpenWeatherApi.retrofitService.getByCityName(
            queryFilter = cityNameAndCountryCode, units = units
        )
        var result = WeatherData()
        try {
            result = getByCityName.await()
            Timber.i("$result")
        } catch (t: Throwable) {
            Timber.e("Failure: ${t.message}")
        }
        return result
    }
}