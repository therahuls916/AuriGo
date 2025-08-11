// In features/home/location/LocationUtils.kt

package com.rahul.auric.aurigo.features.home.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

/**
 * A helper function to get the user's last known location.
 * This is now tailored for Google Maps.
 *
 * @param context The application context.
 * @param onLocationFound A callback that provides the LatLng object of the user's location.
 */
@SuppressLint("MissingPermission") // We promise to check for permissions before calling this.
fun getUserLocation(context: Context, onLocationFound: (LatLng) -> Unit) {
    // Get the FusedLocationProviderClient
    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    // Request the last known location
    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            // If location is found, create a LatLng object
            val userLatLng = LatLng(location.latitude, location.longitude)
            // Fire the callback with the user's location
            onLocationFound(userLatLng)
        }
    }
}