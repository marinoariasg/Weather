package com.marinoariasg.conduentweather

/*
Temperature is available in Fahrenheit, Celsius and Kelvin units.

For temperature in Fahrenheit use units=imperial
For temperature in Celsius use units=metric
Temperature in Kelvin is used by default, no need to use units parameter in API cal
 */
data class WeatherLookUpParameters(
    val cityName: String = "Havant",
    val countryCode: String = "",
    val units: String = "Kelvin"
)