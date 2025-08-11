// In features/home/HomeScreen.kt

package com.rahul.auric.aurigo.features.home

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.rahul.auric.aurigo.features.home.components.DriverCard
import com.rahul.auric.aurigo.features.home.components.HomeSearchBar
import com.rahul.auric.aurigo.features.home.location.getUserLocation
import com.rahul.auric.aurigo.navigation.AppRoutes
import kotlinx.coroutines.launch

data class DriverInfo(
    val name: String,
    val rating: Double,
    val carModel: String,
    val distanceAway: String
)

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val locationPermissionsState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    val defaultLocation = LatLng(28.6139, 77.2090)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 10f)
    }
    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    fun centerOnUserLocation() {
        getUserLocation(context) { location ->
            userLocation = location
            coroutineScope.launch {
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLngZoom(location, 15f),
                    durationMs = 1000
                )
            }
        }
    }

    LaunchedEffect(locationPermissionsState.allPermissionsGranted) {
        if (locationPermissionsState.allPermissionsGranted) {
            centerOnUserLocation()
        } else {
            locationPermissionsState.launchMultiplePermissionRequest()
        }
    }

    val nearbyDrivers = listOf(
        DriverInfo("Sarah J.", 4.9, "Toyota Camry", "2.5 km"),
        DriverInfo("Michael B.", 4.8, "Honda Civic", "3.1 km"),
        DriverInfo("Emily R.", 5.0, "Tesla Model 3", "4.0 km")
    )

    // --- UI ---
    Box(modifier = Modifier.fillMaxSize()) {
        // --- Layer 1: Google Map (Bottom-most layer) ---
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = false,
                zoomControlsEnabled = false
            )
        ) {
            userLocation?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Your Location"
                )
            }
        }

        // --- Layer 2: Search Bar (Aligned to Top) ---
        HomeSearchBar(
            modifier = Modifier.align(Alignment.TopCenter),
            onClick = {
                Toast.makeText(context, "Search bar clicked!", Toast.LENGTH_SHORT).show()
            }
        )

        // --- Layer 3: Driver Cards (Aligned to Bottom) ---
        // This is drawn before the FAB, so it will be underneath it.
        LazyRow(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(nearbyDrivers) { driver ->
                DriverCard(
                    driverName = driver.name,
                    rating = driver.rating,
                    carModel = driver.carModel,
                    distanceAway = driver.distanceAway,
                    onSelect = {
                        navController.navigate(AppRoutes.RideDetailsScreen.route)
                    }
                )
            }
        }

        // --- Layer 4: Re-center FAB (Aligned to Bottom-End, Top-most layer in this corner) ---
        // This is now declared AFTER the LazyRow, so it will be drawn ON TOP.
        FloatingActionButton(
            onClick = { centerOnUserLocation() },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .navigationBarsPadding(),
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Default.MyLocation, contentDescription = "Re-center map")
        }
    }
}