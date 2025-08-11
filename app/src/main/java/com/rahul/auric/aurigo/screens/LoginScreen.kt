// In screens/LoginScreen.kt

package com.rahul.auric.aurigo.screens

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType // Simplified import
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.rahul.auric.aurigo.R
import com.rahul.auric.aurigo.components.SocialLoginButton
import com.rahul.auric.aurigo.ui.theme.AurigoTheme
import java.util.concurrent.TimeUnit

@Composable
fun LoginScreen(navController: NavController) {
    val activity = LocalContext.current as Activity
    var phoneNumber by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Get Firebase instance once
    val auth = FirebaseAuth.getInstance()

    // Firebase Auth Callbacks
    val callbacks = remember { // Use remember to avoid recreating callbacks on recomposition
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("Auth", "Verification Completed: $credential")
                Toast.makeText(context, "Verification Completed", Toast.LENGTH_SHORT).show()
                // TODO: Sign in user with credential
            }
            override fun onVerificationFailed(e: FirebaseException) {
                Log.w("Auth", "Verification Failed", e)
                Toast.makeText(context, "Verification Failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d("Auth", "Code Sent. Verification ID: $verificationId")
                Toast.makeText(context, "OTP Sent Successfully!", Toast.LENGTH_SHORT).show()
                // TODO: Navigate to an OTP screen, passing the verificationId
            }
        }
    }

    // This function will contain the logic for sending the OTP
    // This function will contain the logic for sending the OTP
    fun sendOtp() {
        // Corrected to use the Indian country code +91
        val fullPhoneNumber = "+91$phoneNumber"

        // Simple validation: check if the phone number (without country code) is 10 digits
        if (phoneNumber.length != 10) {
            Toast.makeText(context, "Please enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show()
            return // Stop the function here if the number is invalid
        }

        Toast.makeText(context, "Sending OTP to $fullPhoneNumber...", Toast.LENGTH_SHORT).show()

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(fullPhoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(activity) // Activity (for solving reCAPTCHA)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // --- UI Layout ---

        // Top Section
        IconButton(onClick = { /* TODO */ }) {
            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "Log in or sign up", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(32.dp))

        // Phone Section
        OutlinedTextField(
            value = "India (+91)", // <-- THIS IS THE UI CORRECTION
            onValueChange = {},
            readOnly = true, // Still read-only for now, as it's a placeholder
            label = { Text("Country/Region") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone number") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "We'll call or text to confirm your number. Standard message and data rates apply.",
            fontSize = 12.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { sendOtp() }, // Call our clean function
            modifier = Modifier.fillMaxWidth().height(50.dp),
            shape = RoundedCornerShape(8.dp),
            enabled = phoneNumber.length > 5 // Simple validation
        ) {
            Text(text = "Continue", fontSize = 16.sp)
        }
        TextButton(onClick = { /* TODO */ }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Forgot Password?", fontWeight = FontWeight.Medium)
        }

        // Divider
        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Divider(modifier = Modifier.weight(1f))
            Text("or", modifier = Modifier.padding(horizontal = 8.dp), color = Color.Gray)
            Divider(modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(24.dp))

        // Social Logins
        SocialLoginButton(text = "Continue with email", icon = Icons.Default.MailOutline, onClick = { /*TODO*/ })
        Spacer(modifier = Modifier.height(16.dp))
        SocialLoginButton(text = "Continue with Apple", icon = painterResource(id = R.drawable.ic_apple), onClick = { /*TODO*/ })
        Spacer(modifier = Modifier.height(16.dp))
        SocialLoginButton(text = "Continue with Google", icon = painterResource(id = R.drawable.ic_google), onClick = { /*TODO*/ })
        Spacer(modifier = Modifier.height(16.dp))
        SocialLoginButton(text = "Continue with Facebook", icon = painterResource(id = R.drawable.ic_facebook), onClick = { /*TODO*/ })
        Spacer(modifier = Modifier.height(16.dp))

        // Create Account Link
        TextButton(onClick = { /* TODO */ }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Create an Account", fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    AurigoTheme {
        LoginScreen(rememberNavController())
    }
}