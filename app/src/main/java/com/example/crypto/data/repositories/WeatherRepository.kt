package com.example.crypto.data.repositories

import com.example.crypto.data.data_sources.remote.weather.WeatherResponse

interface WeatherRepository {
    suspend fun getWeather(queryString: String): WeatherResponse;
}