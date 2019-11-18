package com.marinoariasg.conduentweather

data class WeatherLookUpParameters (val cityName: String,
                                    val countryCode: String = "",
                                    val units: String = "metric")