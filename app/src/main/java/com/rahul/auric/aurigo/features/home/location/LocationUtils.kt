// In features/home/location/LocationUtils.kt

package com.rahul.auric.aurigo.features.home.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

/**
 * Checks if the device's GPS or Network location providers are enabled.
 */
fun isLocationEnabled(context: Context): Boolean {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
}

/**
 * A helper function to get the user's last known location.
 * Now includes a check to see if location services are enabled first.
 *
 * @param context The application context.
 * @param onLocationFound A callback that provides the LatLng object of the user's location.
 */
@SuppressLint("MissingPermission")
fun getUserLocation(context: Context, onLocationFound: (LatLng) -> Unit) {
    if (!isLocationEnabled(context)) {
        // If location is off, show a message and don't proceed.
        Toast.makeText(context, "Please turn on your GPS/Location Services", Toast.LENGTH_LONG).show()
        return
    }

    val fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.lastLocation.addOnSuccessListener { location ->
        if (location != null) {
            val userLatLng = LatLng(location.latitude, location.longitude)
            onLocationFound(userLatLng)
        } else {
            // This can happen if the location has never been recorded.
            Toast.makeText(context, "Could not retrieve location. Please move a little and try again.", Toast.LENGTH_LONG).show()
        }
    }
}