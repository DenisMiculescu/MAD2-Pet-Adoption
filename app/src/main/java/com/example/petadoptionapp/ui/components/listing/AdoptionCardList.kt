package com.example.petadoptionapp.ui.components.listing

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.data.fakeAdoptions
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme

@Composable
internal fun AdoptionCardList(
    adoptions: List<AdoptionModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = adoptions,
            key = { adoption -> adoption.petName }
        ) { adoption ->
            AdoptionCard(adoption = adoption)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AdoptionCardListPreview() {
    PetAdoptionAppTheme {
        AdoptionCardList(
            fakeAdoptions.toMutableStateList()
        )
    }
}