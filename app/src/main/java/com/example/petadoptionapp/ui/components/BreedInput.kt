package com.example.petadoptionapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BreedInput(
    breed: String,
    onBreedChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = "Breed:")
        TextField(
            value = breed,
            onValueChange = onBreedChange,
            label = { Text("Enter breed") },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun BreedInputPreview() {
    BreedInput(breed = "", onBreedChange = {})
}
