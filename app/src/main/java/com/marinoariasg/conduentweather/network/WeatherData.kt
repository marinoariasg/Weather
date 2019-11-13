package com.marinoariasg.conduentweather.network

import com.marinoariasg.conduentweather.network.weatherParameters.*
import com.squareup.moshi.Json

/**
 * Weather parameters in API response
 */
data class WeatherData(

    // City geo location
    @Json(name = "coord") val coord: Coord? = null,

    // more info Weather condition codes.
    @Json(name = "weather") val weather: List<Weather>? = null,

    // Internal parameter
    @Json(name = "base") val base: String? = null,

    @Json(name = "main") val main: Main? = null,
    @Json(name = "wind") val wind: Wind? = null,
    @Json(name = "clouds") val clouds: Clouds? = null,
    @Json(name = "rain") var rain: Rain? = null,
    @Json(name = "snow") var snow: Snow? = null,

    // Time of data calculation, unix, UTC
    @Json(name = "dt") val timeOfDataCalculation: Long? = null,

    @Json(name = "sys") var sys: Sys? = null,

    @Json(name = "visibility") val visibility: Int? = null,

    // Shift in seconds from UTC
    @Json(name = "timezone") var timeZone: Int? = null,
    // City ID
    @Json(name = "id") var cityId: String? = null,
    // City name
    @Json(name = "name") var cityName: String? = null,
    // Internal parameter
    @Json(name = "cod") var internalParameter: Int? = null
) {

}