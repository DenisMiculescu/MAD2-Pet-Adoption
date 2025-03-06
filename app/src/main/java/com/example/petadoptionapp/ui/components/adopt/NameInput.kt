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
fun NameInput(
    name: String,
    onNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(text = "Pet Name:")
        TextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Enter pet name") },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun NameInputPreview() {
    NameInput(name = "", onNameChange = {})
}
