package com.marinoariasg.conduentweather.network.weatherParameters

import com.squareup.moshi.Json

data class Snow(
    // Snow volume for the last 1 hour, mm
    @Json(name = "1h") val volumeForTheLastHour: Double?,
    // Snow volume for the last 3 hours, mm
    @Json(name = "3h") val volumeForTheLastThreeHours: Double?
)