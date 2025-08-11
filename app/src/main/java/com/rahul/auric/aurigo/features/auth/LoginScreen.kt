// In screens/LoginScreen.kt

package com.rahul.auric.aurigo.features.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.rahul.auric.aurigo.navigation.AppRoutes
import com.rahul.auric.aurigo.ui.theme.AurigoTheme

@Composable
fun LoginScreen(navController: NavController) {
    var phoneNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // --- UI State Variables ---
    var passwordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) } // To show/hide loading indicator

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    fun handleAuthAction(isLogin: Boolean) {
        if (phoneNumber.length != 10 || password.length < 6) {
            Toast.makeText(context, "Please enter a valid 10-digit number and a password of at least 6 characters.", Toast.LENGTH_LONG).show()
            return
        }
        isLoading = true // Show loading indicator
        val email = "+91$phoneNumber@aurigo.app"

        val authAction = if (isLogin) {
            auth.signInWithEmailAndPassword(email, password)
        } else {
            auth.createUserWithEmailAndPassword(email, password)
        }

        authAction.addOnCompleteListener { task ->
            isLoading = false // Hide loading indicator
            if (task.isSuccessful) {
                if (isLogin) {
                    Toast.makeText(context, "Login Successful!", Toast.LENGTH_SHORT).show()
                    navController.navigate(AppRoutes.HomeScreen.route) {
                        popUpTo(AppRoutes.LoginScreen.route) { inclusive = true }
                    }
                } else {
                    Toast.makeText(context, "Account Created Successfully! Please log in.", Toast.LENGTH_LONG).show()
                }
            } else {
                val errorMsg = if (isLogin) "Login Failed" else "Sign Up Failed"
                Toast.makeText(context, "$errorMsg: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp) // Increased padding for better spacing
                .verticalScroll(rememberScrollState())
        ) {
            // Top Section
            IconButton(onClick = { /* TODO */ }) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Log in or sign up", fontSize = 28.sp, fontWeight = FontWeight.Bold) // Slightly larger text
            Spacer(modifier = Modifier.height(40.dp))

            // Phone and Password Section
            OutlinedTextField(
                value = "India (+91)", onValueChange = {}, readOnly = true,
                label = { Text("Country/Region") }, modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = phoneNumber, onValueChange = { phoneNumber = it },
                label = { Text("Phone number") }, modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                enabled = !isLoading // Disable when loading
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password, onValueChange = { password = it },
                label = { Text("Password") }, modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                enabled = !isLoading, // Disable when loading
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = { // NEW: Password visibility toggle
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    val description = if (passwordVisible) "Hide password" else "Show password"
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = description)
                    }
                }
                                  )
            Spacer(modifier = Modifier.height(24.dp))

            // Login Button with Loading Indicator
            Button(
                onClick = { handleAuthAction(isLogin = true) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(8.dp),
                enabled = !isLoading // Disable button when loading
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary // Make spinner white on blue button
                    )
                } else {
                    Text(text = "Log In", fontSize = 16.sp)
                }
            }
            // (Forgot Password and other elements can remain as they are)

            // ...

            // Create Account Link
            TextButton(
                onClick = { handleAuthAction(isLogin = false) },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                enabled = !isLoading
            ) {
                Text(text = "Create an Account", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

// Preview remains the same
@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AurigoTheme {
        LoginScreen(rememberNavController())
    }
}