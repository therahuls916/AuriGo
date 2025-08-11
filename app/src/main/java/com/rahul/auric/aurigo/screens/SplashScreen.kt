// In screens/SplashScreen.kt

package com.rahul.auric.aurigo.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.rahul.auric.aurigo.R
import com.rahul.auric.aurigo.navigation.AppRoutes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    // This side-effect runs once when the screen is displayed
    LaunchedEffect(key1 = true) {
        delay(2000L) // Wait for 2 seconds

        // This is the corrected navigation logic
        navController.navigate(AppRoutes.LoginScreen.route) {
            // This is the critical change. We clear the entire navigation stack
            // and make the LoginScreen the new starting point.
            popUpTo(navController.graph.startDestinationId) {
                inclusive = true
            }
        }
    }

    // The UI layout remains the same
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.aurigo_logo),
            contentDescription = "AuriGO Logo"
        )
    }
}