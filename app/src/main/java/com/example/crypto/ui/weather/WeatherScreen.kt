package com.example.crypto.ui.weather

import android.Manifest
import android.content.Context
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.crypto.common.getLocation
import com.example.crypto.ui.weather.state.WeatherViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices


@Composable
fun WeatherScreen(context: Context = LocalContext.current, viewModel: WeatherViewModel = hiltViewModel()){


    getLocation(
        context = context,
        // edit your location in simulator otherwise it's always gonna default to somewhere in the US
        // if you install on a phone it'll get your actual location
        onLocationResult = {
            location->
            Log.d("Location test", "$location")
            viewModel.getWeather("${location.latitude} ${location.longitude}")
        },
        onLocationFailure = {

        },
        // can't bother adding this if you deny permission reinstall and rerun
        onLocationDenied = {

        },
    )

    val state = viewModel.state.value

    Column(modifier = Modifier.padding(16.dp)) {

        when {
            state.isLoading -> CircularProgressIndicator()
            state.error.isNotEmpty() -> Text(text = state.error, color = Color.Red)
            state.weather != null -> {
                val weather = state.weather
                val current = weather.current

                Text("Weather in ${weather.location.name}, ${weather.location.country}")
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("${current.temp_c}°C")
                        Text(current.condition.text)
                    }
                }
            }
        }
    }
}