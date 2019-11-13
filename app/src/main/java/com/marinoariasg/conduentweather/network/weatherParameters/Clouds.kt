package com.marinoariasg.conduentweather.network.weatherParameters

import com.squareup.moshi.Json

data class Clouds(
    // Cloudiness, %
    @Json(name = "all") val cloudiness: Int?
)