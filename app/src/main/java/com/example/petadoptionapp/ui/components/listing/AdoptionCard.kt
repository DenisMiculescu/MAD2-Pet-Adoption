package com.example.petadoptionapp.ui.components.listing

import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.petadoptionapp.R

@Composable
fun AdoptionCard(
    petName: String,
    petType: String,
    petBreed: String,
    ageYear: Int,
    chipped: String,
    location: String,
    dateListed: String,
    dateModified: String,
    ownerName: String,
    ownerContact: String,
    photoUri: Uri,
    onClickAdoptionDetails: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        AdoptionCardContent(
            petName,
            petType,
            petBreed,
            ageYear,
            chipped,
            location,
            dateListed,
            dateModified,
            ownerName,
            ownerContact,
            photoUri,
            onClickAdoptionDetails,
        )
    }
}

@Composable
private fun AdoptionCardContent(
    petName: String,
    petType: String,
    petBreed: String,
    ageYear: Int,
    chipped: String,
    location: String,
    dateListed: String,
    dateModified: String,
    ownerName: String,
    ownerContact: String,
    photoUri: Uri,
    onClickAdoptionDetails: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var showDeleteConfirmDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(photoUri)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = petName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = "$petType • $petBreed",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "$ageYear yrs",
                    style = MaterialTheme.typography.bodyMedium
                )
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
        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Chipped: $chipped",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Owner: $ownerName (${ownerContact})",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Location: $location",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Date Listed: $dateListed", style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Modified $dateModified", style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FilledTonalButton(onClick = onClickAdoptionDetails) {
                    Text(text = "Show More")
                }
            }
            if (showDeleteConfirmDialog) {
                ShowDeleteAlert(
                    onDismiss = { showDeleteConfirmDialog = false },
                )
            }
        }
    }
}

@Composable
fun ShowDeleteAlert(
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = { Text(stringResource(id = R.string.confirm_delete)) },
        text = { Text(stringResource(id = R.string.confirm_delete_message)) },
        confirmButton = {
            Button(
                onClick = {
                }
            ) { Text("Yes") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("No") }
        }
    )
}