package com.example.crypto.common

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
@Composable
fun getLocation(
    context: Context,
    onLocationResult: (Location) -> Unit,
    onLocationFailure: () -> Unit,
    onLocationDenied: () -> Unit
) {
    val fusedLocationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                fusedLocationClient.lastLocation
                    .addOnSuccessListener { location ->
                        onLocationResult(location)
                    }
                    .addOnFailureListener {
                        onLocationFailure()
                    }
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                onLocationDenied()
            }
        }
    )

    // Request permission
    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}