// In navigation/AppRoutes.kt

package com.rahul.auric.aurigo.navigation

// A sealed class to define the routes for all our screens in a type-safe way
sealed class AppRoutes(val route: String) {
    object LoginScreen : AppRoutes("login_screen")
    object HomeScreen : AppRoutes("home_screen")
    object RideDetailsScreen : AppRoutes("ride_details_screen")
    object BookingConfirmationScreen : AppRoutes("booking_confirmation_screen")
    object RideInProgressScreen : AppRoutes("ride_in_progress_screen")
    object RideHistoryScreen : AppRoutes("ride_history_screen")
    object PaymentScreen : AppRoutes("payment_screen")
    object ProfileScreen : AppRoutes("profile_screen")
    object SupportScreen : AppRoutes("support_screen")
}