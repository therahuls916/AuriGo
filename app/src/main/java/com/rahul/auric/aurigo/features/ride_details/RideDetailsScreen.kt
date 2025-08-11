// In features/ride_details/RideDetailsScreen.kt

package com.rahul.auric.aurigo.features.ride_details

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rahul.auric.aurigo.navigation.AppRoutes
import com.rahul.auric.aurigo.ui.theme.AurigoTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideDetailsScreen(navController: NavController) {

    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope() // Use the lifecycle-aware scope

    fun confirmRideRequest() {
        isLoading = true
        val db = Firebase.firestore
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser == null) {
            Toast.makeText(navController.context, "Error: User not logged in", Toast.LENGTH_SHORT).show()
            isLoading = false
            return
        }

        val rideRequest = hashMapOf(
            "riderId" to currentUser.uid,
            "pickupLocation" to "Dummy Pickup Location, Delhi",
            "destinationLocation" to "Dummy Destination, Gurgaon",
            "status" to "searching_for_driver",
            "fare" to 155.50,
            "requestedAt" to System.currentTimeMillis()
        )

        db.collection("ride_requests")
            .add(rideRequest)
            .addOnSuccessListener {
                Toast.makeText(navController.context, "Ride Requested! Finding driver...", Toast.LENGTH_LONG).show()

                // --- THIS IS THE CORRECTED LOGIC ---
                coroutineScope.launch {
                    delay(5000L) // This delay runs on a background thread.

                    // Switch to the Main thread before navigating
                    withContext(Dispatchers.Main) {
                        navController.navigate(AppRoutes.RideInProgressScreen.route) {
                            popUpTo(AppRoutes.HomeScreen.route)
                        }
                    }
                }
            }
            .addOnFailureListener { e ->
                isLoading = false
                Toast.makeText(navController.context, "Error requesting ride: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ride Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // DUMMY DRIVER INFO CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(60.dp).clip(CircleShape).background(Color.LightGray))
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text("Sarah J.", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Star, contentDescription = "Rating", tint = Color(0xFFFFC107), modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("4.9", color = Color.Gray, fontSize = 14.sp)
                        }
                        Text("Toyota Camry - RN-8765", color = Color.Gray, fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // FARE & ETA CARD
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Estimated Fare", fontSize = 16.sp, color = Color.Gray)
                        Text("₹155.50", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Estimated Arrival", fontSize = 16.sp, color = Color.Gray)
                        Text("12 min", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // CONFIRM RIDE BUTTON
            Button(
                onClick = { confirmRideRequest() },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(8.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text(text = "Confirm Ride", fontSize = 16.sp)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RideDetailsScreenPreview() {
    AurigoTheme {
        RideDetailsScreen(rememberNavController())
    }
}