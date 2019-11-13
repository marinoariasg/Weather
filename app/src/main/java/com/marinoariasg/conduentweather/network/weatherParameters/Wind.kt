package com.marinoariasg.conduentweather.network.weatherParameters

import com.squareup.moshi.Json

data class Wind(
    // Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
    @Json(name = "speed") val speed: Double?,
    // Wind direction, degrees (meteorological)
    @Json(name = "deg") val degrees: Int?
)