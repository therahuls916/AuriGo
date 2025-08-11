// In features/home/location/BitmapHelper.kt

package com.rahul.auric.aurigo.features.home.location

import android.content.Context
import android.graphics.Bitmap
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory

/**
 * A helper function to create a scaled BitmapDescriptor from a drawable resource.
 * This is used for creating custom map markers of a specific size.
 *
 * @param context The application context.
 * @param resId The resource ID of the drawable.
 * @param width The desired width of the marker in pixels.
 * @param height The desired height of the marker in pixels.
 * @return A BitmapDescriptor that can be used for a map marker icon.
 */
fun bitmapDescriptorFromVector(
    context: Context,
    resId: Int,
    width: Int,
    height: Int
): BitmapDescriptor? {
    val vectorDrawable = ContextCompat.getDrawable(context, resId) ?: return null
    vectorDrawable.setBounds(0, 0, width, height)
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = android.graphics.Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}