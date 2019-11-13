package com.marinoariasg.conduentweather.network.weatherParameters

import com.squareup.moshi.Json

data class Coord(
    // City geo location, longitude
    @Json(name = "lon") val lon: Double?,
    // City geo location, latitude
    @Json(name = "lat") val lat: Double?
)