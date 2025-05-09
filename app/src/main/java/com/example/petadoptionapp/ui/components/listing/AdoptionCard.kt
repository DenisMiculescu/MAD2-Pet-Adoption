package com.example.petadoptionapp.ui.components.listing

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.petadoptionapp.R
import com.example.petadoptionapp.data.AdoptionModel
import java.text.DateFormat
import java.util.*

@Composable
fun AdoptionCard(
    adoption: AdoptionModel,
    onClickDelete: () -> Unit,
    onClickAdoptionDetails: () -> Unit,
    onRefreshList: () -> Unit
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
            adoption,
            onClickDelete,
            onClickAdoptionDetails,
            onRefreshList
        )
    }
}

@Composable
private fun AdoptionCardContent(
    adoption: AdoptionModel,
    onClickDelete: () -> Unit,
    onClickAdoptionDetails: () -> Unit,
    onRefreshList: () -> Unit
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
            Icon(
                imageVector = Icons.Default.Pets,
                contentDescription = "Pet Icon",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(40.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = adoption.petName,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = if (adoption.petType == "Dog") "Dog" else "Cat",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Breed: ${adoption.petBreed}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${adoption.ageYear} years",
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
                text = "Chipped: ${adoption.chipped}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Owner Name: ${adoption.ownerName}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Contact: ${adoption.ownerContact}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Location: ${adoption.location}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Listed on: ${DateFormat.getDateInstance().format(adoption.dateListed)}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Modified ${adoption.dateModified}", style = MaterialTheme.typography.labelSmall
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                FilledTonalButton(onClick = onClickAdoptionDetails) {
                    Text(text = "Show More")
                }
                FilledTonalIconButton(onClick = {
                    showDeleteConfirmDialog = true
                }) {
                    Icon(Icons.Filled.Delete, "Delete Adoption")
                }
            }
            if (showDeleteConfirmDialog) {
                ShowDeleteAlert(
                    onDismiss = { showDeleteConfirmDialog = false },
                    onDelete = onClickDelete,
                    onRefresh = onRefreshList
                )
            }
        }
    }
}

@Composable
fun ShowDeleteAlert(
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
    onRefresh: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss ,
        title = { Text(stringResource(id = R.string.confirm_delete)) },
        text = { Text(stringResource(id = R.string.confirm_delete_message)) },
        confirmButton = {
            Button(
                onClick = {
                    onDelete()
                    onRefresh()
                }
            ) { Text("Yes") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("No") }
        }
    )
}