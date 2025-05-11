package com.example.petadoptionapp.ui.components.listing

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.petadoptionapp.R
import com.example.petadoptionapp.data.model.AdoptionModel
import java.text.DateFormat

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun AdoptionCardList(
    adoptions: List<AdoptionModel>,
    modifier: Modifier = Modifier,
    onDeleteAdoption: (AdoptionModel) -> Unit,
    onClickAdoptionDetails: (String) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(
            items = adoptions,
            key = { adoption -> adoption._id }
        ) { adoption ->
            var showDeleteConfirmDialog by remember { mutableStateOf(false) }

            val dismissState = rememberDismissState(
                confirmStateChange = {
                    if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                        showDeleteConfirmDialog = true
                    }
                    false // prevent immediate dismiss to wait for user confirmation
                }
            )

            val cardShape = RoundedCornerShape(12.dp)

            SwipeToDismiss(
                state = dismissState,
                directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                background = {
                    val color = if (dismissState.dismissDirection != null) {
                        MaterialTheme.colors.error
                    } else {
                        Color.Transparent
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .background(color, shape = cardShape),
                        contentAlignment = when (dismissState.dismissDirection) {
                            DismissDirection.StartToEnd -> Alignment.CenterStart
                            DismissDirection.EndToStart -> Alignment.CenterEnd
                            else -> Alignment.Center
                        }
                    ) {
                        if (dismissState.dismissDirection != null) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                },
                dismissContent = {
                    AdoptionCard(
                        petName = adoption.petName,
                        petType = adoption.petType,
                        petBreed = adoption.petBreed,
                        ageYear = adoption.ageYear,
                        chipped = adoption.chipped,
                        location = adoption.location,
                        dateListed = DateFormat.getDateTimeInstance().format(adoption.dateListed),
                        dateModified = DateFormat.getDateTimeInstance().format(adoption.dateModified),
                        ownerName = adoption.ownerName,
                        ownerContact = adoption.ownerContact,
                        photoUri = Uri.parse(adoption.imageUri),
                        onClickAdoptionDetails = { onClickAdoptionDetails(adoption._id) },
                    )

                    if (showDeleteConfirmDialog) {
                        ShowSwipeDeleteAlert(
                            onDelete = {
                                showDeleteConfirmDialog = false
                                onDeleteAdoption(adoption)
                            },
                            onDismiss = {
                                showDeleteConfirmDialog = false
                            }
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun ShowSwipeDeleteAlert(onDelete: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(stringResource(id = R.string.confirm_delete)) },
        text = { Text(stringResource(id = R.string.confirm_delete_message)) },
        confirmButton = {
            Button(onClick = onDelete) {
                Text("Yes")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("No")
            }
        }
    )
}
