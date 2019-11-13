package com.marinoariasg.conduentweather.network.weatherParameters

import com.squareup.moshi.Json

data class Rain(
    // Rain volume for the last 1 hour, mm
    @Json(name = "1h") var volumeForTheLastHour: Double?,
    // Rain volume for the last 3 hours, mm
    @Json(name = "3h") var volumeForTheLastThreeHours: Double?
)