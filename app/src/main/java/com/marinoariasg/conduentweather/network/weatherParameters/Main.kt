package com.marinoariasg.conduentweather.network.weatherParameters

import com.squareup.moshi.Json

data class Main(
    /*
    Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
    Temperature is available in Fahrenheit, Celsius and Kelvin units.
    For temperature in Fahrenheit use units=imperial
    For temperature in Celsius use units=metric
    Temperature in Kelvin is used by default, no need to use units parameter in API call
     */
    @Json(name = "temp") val temperature: Double?,
    // Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
    @Json(name = "pressure") val pressure: Int?,
    // Humidity, %
    @Json(name = "humidity") val humidity: Int?,
    /* Minimum temperature at the moment. This is deviation from current temp that is possible for
    large cities and megalopolises geographically expanded (use these parameter optionally).
    Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit. */
    @Json(name = "temp_min") val temperatureMin: Double?,
    /* Maximum temperature at the moment. This is deviation from current temp that is possible for
    large cities and megalopolises geographically expanded (use these parameter optionally). Unit
    Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.*/
    @Json(name = "temp_max") val temperatureMax: Double?,
    // Atmospheric pressure on the sea level, hPa
    @Json(name = "sea_level") val seaLevel: Double?,
    // Atmospheric pressure on the ground level, hPa
    @Json(name = "grnd_level") val groundLevel: Double?
)