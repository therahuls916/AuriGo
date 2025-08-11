// In features/home/components/HomeSearchBar.kt

package com.rahul.auric.aurigo.features.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HomeSearchBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit // To handle when the user taps it
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(30.dp)),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Where to?", color = Color.Gray)
        }
    }
}