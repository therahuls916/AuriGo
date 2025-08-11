// In navigation/AppNavigation.kt

package com.rahul.auric.aurigo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rahul.auric.aurigo.features.auth.LoginScreen
import com.rahul.auric.aurigo.features.home.HomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.LoginScreen.route
    ) {
        // --- Define all existing screens in the app ---

        // Authentication Feature
        composable(route = AppRoutes.LoginScreen.route) {
            LoginScreen(navController = navController)
        }

        // Home Feature
        composable(route = AppRoutes.HomeScreen.route) {
            HomeScreen(navController = navController)
        }

        // We will add other routes for other features here as we build them.
        // For example:
        // composable(AppRoutes.ProfileScreen.route) { ProfileScreen(navController) }
    }
}