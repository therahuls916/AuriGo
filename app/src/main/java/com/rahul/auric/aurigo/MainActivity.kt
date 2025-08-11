// In MainActivity.kt

package com.rahul.auric.aurigo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rahul.auric.aurigo.navigation.AppNavigation // Import our Nav Graph
import com.rahul.auric.aurigo.ui.theme.AuriGoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuriGoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // This is now the entry point of our app's UI
                    AppNavigation()
                }
            }
        }
    }
}