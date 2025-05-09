package com.example.petadoptionapp.ui.components.listing

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.petadoptionapp.data.model.AdoptionModel

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
                adoption = adoption,
                onClickDelete = { onDeleteAdoption(adoption) },
                onClickAdoptionDetails = { onClickAdoptionDetails(adoption.id) },
                onRefreshList = onRefreshList
            )
        }
    }
}