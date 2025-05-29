package com.example.crypto.ui.weather.state

import com.example.crypto.data.data_sources.remote.weather.WeatherResponse
import com.example.crypto.domain.models.CoinDetail


data class WeatherState(
    val weather: WeatherResponse ?= null,
    val isLoading:Boolean = false,
    val error:String = ""
)