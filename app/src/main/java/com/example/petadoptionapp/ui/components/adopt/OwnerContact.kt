package com.example.petadoptionapp.ui.components.adopt

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OwnerContact (
    ownerContact: String,
    onOwnerContactChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    var isContactValid by remember { mutableStateOf(true) }

    fun isContactValid(ownerContact: String): Boolean {
        return ownerContact.matches(Regex("\\d{3} \\d{3} \\d{4}"))
    }

    Row(modifier = modifier) {
        TextField(
            value = ownerContact,
            onValueChange = {
                isContactValid = isContactValid(it)
                onOwnerContactChange(it)
            },
            isError = !isContactValid,
            label = { Text("Enter Telephone Number") },
            modifier = Modifier.padding(start = 8.dp)
        )
    }
    if (!isContactValid) {
        Text(text = "Please enter a valid phone number! (e.g. XXX XXX XXXX)", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview
@Composable
fun OwnerContactPreview() {
    OwnerContact(ownerContact = "", onOwnerContactChange = {})
}
