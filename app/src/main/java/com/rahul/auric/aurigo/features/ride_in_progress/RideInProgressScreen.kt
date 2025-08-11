// In features/ride_in_progress/RideInProgressScreen.kt

package com.rahul.auric.aurigo.features.ride_in_progress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.rahul.auric.aurigo.R
import com.rahul.auric.aurigo.features.home.location.bitmapDescriptorFromVector
import com.rahul.auric.aurigo.features.ride_in_progress.components.DriverInfoCard
import com.rahul.auric.aurigo.navigation.AppRoutes
import com.rahul.auric.aurigo.ui.theme.AurigoTheme
import kotlinx.coroutines.delay

@Composable
fun RideInProgressScreen(navController: NavController) {

    // --- Hardcoded Data for the Prototype ---
    val driverName = "Rahul Sharma"
    val carDetails = "Toyota Camry • DL 4C AB 1234"
    val driverRating = 4.9

    // A pre-defined route for the car to follow
    val ridePath = listOf(
        LatLng(28.6139, 77.2090), // Delhi Start
        LatLng(28.6150, 77.2100),
        LatLng(28.6165, 77.2125),
        LatLng(28.6180, 77.2150),
        LatLng(28.6200, 77.2180),
        LatLng(28.5983, 77.3243)  // Noida End
    )
    var carPosition by remember { mutableStateOf(ridePath.first()) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(carPosition, 15f)
    }

    // --- NEW: Get context and create scaled icon ---
    val context = LocalContext.current
    val carIcon: BitmapDescriptor? = remember(context) {
        // We are scaling the icon to be 100x100 pixels. Adjust as needed.
        bitmapDescriptorFromVector(context, R.drawable.ic_car_marker, 100, 100)
    }

    // --- Simulation Logic ---
    LaunchedEffect(key1 = true) {
        for (i in 1 until ridePath.size) {
            delay(3000L)
            carPosition = ridePath[i]
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLng(carPosition),
                durationMs = 1500
            )
        }
    }

    // --- UI ---
    Box(modifier = Modifier.fillMaxSize()) {
        // Layer 1: Google Map
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = false)
        ) {
            Polyline(points = ridePath, color = androidx.compose.ui.graphics.Color.Blue, width = 15f)

            // Marker now uses the scaled icon
            Marker(
                state = MarkerState(position = carPosition),
                title = "Driver",
                icon = carIcon
            )
        }

        // Layer 2: Driver Info Card
        DriverInfoCard(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            driverName = driverName,
            driverRating = driverRating,
            carDetails = carDetails,
            onCompleteRide = {
                navController.navigate(AppRoutes.HomeScreen.route) {
                    popUpTo(AppRoutes.HomeScreen.route) { inclusive = true }
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RideInProgressScreenPreview() {
    AurigoTheme {
        RideInProgressScreen(rememberNavController())
    }
}