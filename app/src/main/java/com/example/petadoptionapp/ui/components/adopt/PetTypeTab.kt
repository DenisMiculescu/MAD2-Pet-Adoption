package com.example.petadoptionapp.ui.components.adopt

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PetTypeTabs(
    selectedPetType: String,
    onPetTypeChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f))
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        listOf("Dog", "Cat").forEach { type ->
            val isSelected = type == selectedPetType
            Text(
                text = type,
                modifier = Modifier
                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = if (isSelected) 1f else 0f))
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { onPetTypeChange(type) },
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color = if (isSelected) Color.Black else Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun PreviewPetTabs() {
    PetTypeTabs("Dog", onPetTypeChange = {})
}