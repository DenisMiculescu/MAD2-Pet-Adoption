package com.example.petadoptionapp.ui.components.adopt

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LocationInput(
    location: String,
    onLocationChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        TextField(
            value = location,
            onValueChange = onLocationChange,
            label = { Text("Enter location") },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun LocationInputPreview() {
    LocationInput(location = "", onLocationChange = {})
}
