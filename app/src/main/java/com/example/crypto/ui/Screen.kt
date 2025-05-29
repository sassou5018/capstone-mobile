package com.example.crypto.ui

sealed class Screen(val route:String) {
    data object CoinListScreen : Screen("coin_list_screen")
    data object CoinDetailScreen: Screen("coin_detail_screen")
    data object WeatherScreen: Screen("weather_screen")
}