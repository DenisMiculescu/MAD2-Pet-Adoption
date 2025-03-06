package com.example.petadoptionapp.ui.components.listing

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petadoptionapp.R
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme
import java.text.DateFormat
import java.util.*

@Composable
fun AdoptionCard(
    adoption: AdoptionModel
) {
    Card(
        colors = CardDefaults.cardColors(
        ),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 2.dp)
    ) {
        AdoptionCardContent(adoption)
    }
}

@Composable
private fun AdoptionCardContent(adoption: AdoptionModel) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Pets,
                    contentDescription = "Pet Icon",
                    Modifier.padding(end = 8.dp)
                )
                Text(
                    text = adoption.petName,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "${adoption.ageYear} years, ${adoption.ageMonth} months",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
            Text(
                text = "Breed: ${adoption.petBreed}", style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Location: ${adoption.location}", style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Chipped: ${if (adoption.chipped) "Yes" else "No"}", style = MaterialTheme.typography.labelSmall
            )
            Text(
                text = "Listed on: ${DateFormat.getDateInstance().format(adoption.dateListed)}", style = MaterialTheme.typography.labelSmall
            )
            if (expanded) {
                Text(modifier = Modifier.padding(vertical = 16.dp), text = "Additional details can go here.")
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Preview
@Composable
fun AdoptionCardPreview() {
    PetAdoptionAppTheme {
        AdoptionCard(
            adoption = AdoptionModel(
                petName = "Buddy",
                petType = "Dog",
                petBreed = "Golden Retriever",
                ageYear = 3,
                ageMonth = 6,
                chipped = true,
                location = "City Center",
                dateListed = Date()
            )
        )
    }
}