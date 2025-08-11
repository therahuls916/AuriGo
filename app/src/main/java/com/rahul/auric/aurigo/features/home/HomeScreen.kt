// In features/home/HomeScreen.kt

package com.rahul.auric.aurigo.features.home

import android.Manifest
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.rahul.auric.aurigo.features.home.location.getUserLocation

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    // --- THIS IS THE CORRECTED LINE ---
    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    val context = LocalContext.current

    // State for the map
    val defaultLocation = LatLng(28.6139, 77.2090) // Delhi
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 10f)
    }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    // Logic to handle permissions and get location
    LaunchedEffect(locationPermissionsState.allPermissionsGranted) {
        if (locationPermissionsState.allPermissionsGranted) {
            // If permissions are granted, get the user's location
            getUserLocation(context) { location ->
                userLocation = location
                // Move the camera to the user's location
                cameraPositionState.position = CameraPosition.fromLatLngZoom(location, 14f)
            }
        } else {
            // If permissions are not granted, request them
            locationPermissionsState.launchMultiplePermissionRequest()
        }
    }

    // UI
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            // Add a marker at the user's location if it's available
            userLocation?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Your Location"
                )
            }
        }

        // We will add other UI elements like search bars here later
    }
}