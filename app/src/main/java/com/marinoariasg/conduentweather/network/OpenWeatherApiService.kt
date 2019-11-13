package com.marinoariasg.conduentweather.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/"
private const val API_KEY = "1f996ae109bde37ce5565fda4eb07438"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()



interface OpenWeatherApiService {
    /*
    By city name
    API call:
    api.openweathermap.org/data/2.5/weather?q={city name}
    api.openweathermap.org/data/2.5/weather?q={city name},{country code}
     */
    @GET("data/2.5/weather/")
    fun getByCityName(
        @Query("q") queryFilter: String,
        @Query("units") units: String?,
        // API_key as default parameter
        @Query("APPID") apiKey: String = API_KEY
    ): Deferred<WeatherData>

    /*
    Parameters:
    id City ID
    Examples of API calls:
    api.openweathermap.org/data/2.5/weather?id=2172797
     */
    @GET("data/2.5/weather/")
    fun getByCityId(
        @Query("id") cityId: Int,
        @Query("units") units: String?,
        // API_key as default parameter
        @Query("APPID") apiKey: String = API_KEY
    ): Deferred<WeatherData>

    /*
    Parameters:
    lat, lon coordinates of the location of your interest
    Examples of API calls:
    api.openweathermap.org/data/2.5/weather?lat=35&lon=139
     */
    @GET("data/2.5/weather/")
    fun getByGeographicCoordinates(
        @Query("lat") latitude: Int,
        @Query("lon") longitude: Int,
        @Query("units") units: String?,
        // API_key as default parameter
        @Query("APPID") apiKey: String = API_KEY
    ): Deferred<WeatherData>


    /*
    Description:
    Please note if country is not specified then the search works for USA as a default.
    API call:
    api.openweathermap.org/data/2.5/weather?zip={zip code},{country code}
    Examples of API calls:
    api.openweathermap.org/data/2.5/weather?zip=94040,us
     */
    @GET("data/2.5/weather/")
    fun getByZipCode(
        // TODO: Finish appending this:
        @Query("zip") zipCode: String,
        @Query("units") units: String?,
        // API_key as default parameter
        @Query("APPID") apiKey: String = API_KEY
    ): Deferred<WeatherData>

}

// Retrofit call is expensive an our app only needs one retrofit service instance we expose the
// retrofit service to the rest of the application using a public object:
object OpenWeatherApi {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        //.addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    val retrofitService: OpenWeatherApiService by lazy {
        retrofit.create(OpenWeatherApiService::class.java)
    }

}
