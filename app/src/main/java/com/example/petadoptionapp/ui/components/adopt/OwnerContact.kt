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
fun OwnerContact (
    ownerContact: String,
    onOwnerContactChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        TextField(
            value = ownerContact,
            onValueChange = onOwnerContactChange,
            label = { Text("Enter Telephone Number") },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview
@Composable
fun OwnerContactPreview() {
    OwnerContact(ownerContact = "", onOwnerContactChange = {})
}
