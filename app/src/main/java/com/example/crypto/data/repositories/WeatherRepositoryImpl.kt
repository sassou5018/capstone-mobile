package com.example.crypto.data.repositories

import com.example.crypto.common.Constants
import com.example.crypto.data.data_sources.remote.weather.WeatherApi
import com.example.crypto.data.data_sources.remote.weather.WeatherResponse
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor (
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getWeather(queryString: String): WeatherResponse {
        return weatherApi.queryWeather(queryString, Constants.WEATHER_KEY);
    }
}