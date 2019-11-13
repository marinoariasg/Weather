package com.marinoariasg.conduentweather.network.weatherParameters

import com.squareup.moshi.Json

data class Sys(
    // Internal parameter
    @Json(name = "type") var type: Int?,
    // Internal parameter
    @Json(name = "id") var id: Int?,
    // Internal parameter
    @Json(name = "message") var message: String?,
    // Country code (GB, JP etc.)
    @Json(name = "country") var country: String?,
    // Sunrise time, unix, UTC
    @Json(name = "sunrise") var sunrise: Long?,
    // Sunset time, unix, UTC
    @Json(name = "sunset") var sunset: Long?
)