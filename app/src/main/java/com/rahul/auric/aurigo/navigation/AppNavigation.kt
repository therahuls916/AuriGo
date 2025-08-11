// In navigation/AppNavigation.kt

package com.rahul.auric.aurigo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rahul.auric.aurigo.screens.* // Import all your screens

@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Create a controller to manage navigation

    NavHost(
        navController = navController,
        startDestination = AppRoutes.SplashScreen.route // The first screen to show
    ) {
        // Define the path for each screen
        composable(AppRoutes.SplashScreen.route) { SplashScreen() }
        composable(AppRoutes.LoginScreen.route) { LoginScreen() }
        composable(AppRoutes.HomeScreen.route) { HomeScreen() }
        composable(AppRoutes.RideDetailsScreen.route) { RideDetailsScreen() }
        composable(AppRoutes.BookingConfirmationScreen.route) { BookingConfirmationScreen() }
        composable(AppRoutes.RideInProgressScreen.route) { RideInProgressScreen() }
        composable(AppRoutes.RideHistoryScreen.route) { RideHistoryScreen() }
        composable(AppRoutes.PaymentScreen.route) { PaymentScreen() }
        composable(AppRoutes.ProfileScreen.route) { ProfileScreen() }
        composable(AppRoutes.SupportScreen.route) { SupportScreen() }
    }
}