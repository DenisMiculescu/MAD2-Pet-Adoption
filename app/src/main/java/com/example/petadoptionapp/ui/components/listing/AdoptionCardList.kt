package com.example.petadoptionapp.ui.components.listing

import android.net.Uri
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.petadoptionapp.data.model.AdoptionModel
import java.text.DateFormat

@Composable
internal fun AdoptionCardList(
    adoptions: List<AdoptionModel>,
    modifier: Modifier = Modifier,
    onDeleteAdoption: (AdoptionModel) -> Unit,
    onClickAdoptionDetails: (String) -> Unit,
    onRefreshList: () -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(
            items = adoptions,
            key = { adoption -> adoption._id }
        ) { adoption ->
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
                onClickDelete = { onDeleteAdoption(adoption) },
                onClickAdoptionDetails = { onClickAdoptionDetails(adoption._id) },
            )
        }
    }
}