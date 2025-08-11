// In navigation/AppNavigation.kt

package com.rahul.auric.aurigo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rahul.auric.aurigo.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.LoginScreen.route // <-- CHANGE THIS
    ) {
        // DELETE THE COMPOSABLE FOR THE SPLASH SCREEN
        // composable(AppRoutes.SplashScreen.route) { SplashScreen(navController) } // <-- DELETE THIS

        // The app now starts with the Login Screen
        composable(AppRoutes.LoginScreen.route) { LoginScreen(navController) }
        composable(AppRoutes.HomeScreen.route) { HomeScreen() }
        composable(AppRoutes.RideDetailsScreen.route) { RideDetailsScreen() }
        composable(AppRoutes.BookingConfirmationScreen.route) { BookingConfirmationScreen() }
        composable(AppRoutes.RideInProgressScreen.route) { RideInProgressScreen() }
        composable(AppRoutes.RideHistoryScreen.route) { RideHistoryScreen() }
        composable(AppRoutes.PaymentScreen.route) { PaymentScreen() }
        composable(AppRoutes.ProfileScreen.route) { ProfileScreen() }
        composable(AppRoutes.SupportScreen.route) { SupportScreen() }

        composable(
            route = AppRoutes.OtpScreen.route,
            arguments = listOf(navArgument("verificationId") { type = NavType.StringType })
        ) { backStackEntry ->
            val verificationId = backStackEntry.arguments?.getString("verificationId")
            OtpScreen(navController = navController, verificationId = verificationId)
        }
    }
}