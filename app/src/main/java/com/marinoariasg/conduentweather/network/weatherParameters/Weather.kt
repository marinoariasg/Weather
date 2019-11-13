package com.marinoariasg.conduentweather.network.weatherParameters

import com.squareup.moshi.Json

data class Weather(
    // Weather condition id
    @Json(name = "id") val id: Int?,
    // Group of weather parameters (Rain, Snow, Extreme etc.)
    @Json(name = "main") var main: String?,
    // Weather condition within the group
    @Json(name = "description") var description: String?,
    // Weather icon id
    @Json(name = "icon") var icon: String?
)