package com.example.crypto.data.data_sources.remote.weather

import com.google.android.gms.common.api.internal.ApiKey
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("/v1/current.json")
    suspend fun queryWeather(@Query("q") queryString: String, @Query("key") apiKey: String, @Query("aqi") aqi: String = "no"): WeatherResponse
}