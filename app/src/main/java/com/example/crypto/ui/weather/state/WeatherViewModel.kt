package com.example.crypto.ui.weather.state

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto.common.Resource
import com.example.crypto.domain.use_cases.GetWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _state = mutableStateOf(WeatherState())
    val state: State<WeatherState> = _state

    fun getWeather(query: String) {
        getWeatherUseCase(query).onEach { result ->
            when (result) {
                is Resource.Loading -> _state.value = WeatherState(isLoading = true)
                is Resource.Success -> _state.value = WeatherState(weather = result.data)
                is Resource.Error -> _state.value = WeatherState(error = result.message ?: "Unexpected error")
            }
        }.launchIn(viewModelScope)
    }
}
