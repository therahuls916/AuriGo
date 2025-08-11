// In features/home/components/MapView.kt

package com.rahul.auric.aurigo.features.home.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun GoogleMapView(
    modifier: Modifier = Modifier
    // We will add parameters for location later
) {
    // A default location to show if the user's location isn't available yet (e.g., Delhi, India)
    val defaultLocation = LatLng(28.6139, 77.2090)

    // Remember the camera position state
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 10f)
    }

    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    )
}