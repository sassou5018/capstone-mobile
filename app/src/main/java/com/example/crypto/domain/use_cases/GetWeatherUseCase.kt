package com.example.crypto.domain.use_cases

import com.example.crypto.common.Resource
import com.example.crypto.data.data_sources.remote.dto.toCoinDetail
import com.example.crypto.data.data_sources.remote.weather.WeatherResponse
import com.example.crypto.data.repositories.CoinRepository
import com.example.crypto.data.repositories.WeatherRepository
import com.example.crypto.domain.models.CoinDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(queryString:String): Flow<Resource<WeatherResponse>> = flow {
        try {
            emit(Resource.Loading())
            val weather = repository.getWeather(queryString)
            emit(Resource.Success(weather))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(Resource.Error(e.localizedMessage ?: "Http error"))
        } catch (e: IOException) {
            e.printStackTrace();
            emit(Resource.Error("Check your internet connection"))
        }
    }
}