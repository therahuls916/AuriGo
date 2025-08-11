// In navigation/AppNavigation.kt

package com.rahul.auric.aurigo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.rahul.auric.aurigo.features.auth.LoginScreen
import com.rahul.auric.aurigo.features.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // --- THIS IS THE KEY LOGIC ---
    // Check if a user is already logged in with Firebase.
    val firebaseUser = FirebaseAuth.getInstance().currentUser

    // Determine the starting screen based on whether the user is logged in.
    val startDestination = if (firebaseUser != null) {
        // User is logged in, start at Home
        AppRoutes.HomeScreen.route
    } else {
        // User is not logged in, start at Login
        AppRoutes.LoginScreen.route
    }

    NavHost(
        navController = navController,
        startDestination = startDestination // Use our determined starting screen
    ) {
        // Authentication Feature
        composable(route = AppRoutes.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        // Home Feature
        composable(route = AppRoutes.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        // We will add other routes for other features here as we build them.
    }
}