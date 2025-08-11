// In features/ride_in_progress/components/DriverInfoCard.kt

package com.rahul.auric.aurigo.features.ride_in_progress.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rahul.auric.aurigo.R
import com.rahul.auric.aurigo.navigation.AppRoutes

@Composable
fun DriverInfoCard(
    modifier: Modifier = Modifier,
    driverName: String,
    driverRating: Double,
    carDetails: String,
    onCompleteRide: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            // Driver Info Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile_placeholder),
                    contentDescription = "Driver",
                    modifier = Modifier.size(60.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(driverName, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = "Rating", tint = Color.Gray, modifier = Modifier.size(16.dp))
                        Text(" $driverRating", color = Color.Gray)
                    }
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Call, contentDescription = "Call Driver", modifier = Modifier.size(28.dp))
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Default.Message, contentDescription = "Message Driver", modifier = Modifier.size(28.dp))
                }
            }
            Divider(modifier = Modifier.padding(vertical = 16.dp))
            Text(carDetails, color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))

            // "Complete Ride" Button
            Button(
                onClick = onCompleteRide,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Complete Ride")
            }
        }
    }
}