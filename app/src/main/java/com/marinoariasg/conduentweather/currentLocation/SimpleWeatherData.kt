package com.marinoariasg.conduentweather.currentLocation

data class SimpleWeatherData (val cityName: String,
                              val countryCode: String = "",
                              val units: String = "metric")