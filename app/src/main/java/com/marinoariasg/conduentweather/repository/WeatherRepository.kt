package com.marinoariasg.conduentweather.repository

import com.marinoariasg.conduentweather.network.OpenWeatherApi
import com.marinoariasg.conduentweather.network.WeatherData
import timber.log.Timber

class WeatherRepository {

    // TODO: change the try catch to one single fun

    suspend fun cityNameData(cityName: String, countryCode: String, units: String? = null)
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

    suspend fun cityByIdData(cityId: Int, units: String? = null): WeatherData {

        var getByCityId = OpenWeatherApi.retrofitService.getByCityId(
            cityId = cityId, units = units
        )
        var result = WeatherData()
        try {
            result = getByCityId.await()
            Timber.i("$result")
        } catch (t: Throwable) {
            Timber.e("Failure: ${t.message}")
        }
        return result
    }

    suspend fun cityByLatAndLon(lat: Double, lon: Double, units: String? = null): WeatherData {
        val getCityByLatAndLon =
            OpenWeatherApi.retrofitService.getByGeographicCoordinates(
                latitude = lat, longitude = lon, units = units
            )
        // Initialize with default values
        var result = WeatherData()
        try {
            result = getCityByLatAndLon.await()
            Timber.i("$result")
        } catch (t: Throwable) {
            Timber.e("Failure: ${t.message}")
        }
        return result
    }

    suspend fun cityByZipCode(zipCode: String, countryCode: String, units: String? = null): WeatherData {

        // Enforce this: zip={zip code},{country code} for weather api
        val zipCodeAndCountryCode = when (countryCode) {
            "" -> zipCode
            else -> "${zipCode},${countryCode}"
        }

        val getCityByZipCode = OpenWeatherApi.retrofitService.getByZipCode(
            zipCode = zipCodeAndCountryCode, units = units
        )
        // Initialize with default values
        var result = WeatherData()
        try {
            result = getCityByZipCode.await()
            Timber.i("$result")
        } catch (t: Throwable) {
            Timber.e("Failure: ${t.message}")
        }
        return result
    }
}