// In features/home/components/DriverCard.kt

package com.rahul.auric.aurigo.features.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import com.rahul.auric.aurigo.R // Make sure you have a placeholder image in res/drawable

@Composable
fun DriverCard(
    modifier: Modifier = Modifier,
    driverName: String,
    rating: Double,
    carModel: String,
    distanceAway: String,
    onSelect: () -> Unit
) {
    Card(
        modifier = modifier.width(280.dp), // A fixed width for the card
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Driver Picture (using a placeholder for now)
            Image(
                painter = painterResource(id = R.drawable.ic_profile_placeholder), // Add a placeholder image
                contentDescription = "Driver profile picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Driver Name
            Text(text = driverName, fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(4.dp))

            // Rating
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = Color(0xFFFFC107), // Amber/Gold color for the star
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = rating.toString(), color = Color.Gray, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Car Model and Distance
            Text(text = carModel, fontSize = 16.sp)
            Text(text = "$distanceAway away", color = Color.Gray, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(16.dp))

            // Select Driver Button
            Button(
                onClick = onSelect,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray.copy(alpha = 0.5f),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Select Driver")
            }
        }
    }
}