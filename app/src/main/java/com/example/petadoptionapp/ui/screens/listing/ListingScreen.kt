package com.example.petadoptionapp.ui.screens.listing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petadoptionapp.R
import com.example.petadoptionapp.data.model.AdoptionModel
import com.example.petadoptionapp.ui.components.general.Centre
import com.example.petadoptionapp.ui.components.general.ShowError
import com.example.petadoptionapp.ui.components.general.ShowLoader
import com.example.petadoptionapp.ui.components.general.ShowRefreshList
import com.example.petadoptionapp.ui.components.listing.AdoptionCardList
import com.example.petadoptionapp.ui.components.listing.ListingText

@Composable
fun ListingScreen(
    modifier: Modifier = Modifier,
    onClickAdoptionDetails: (String) -> Unit,
    listingViewModel: ListingViewModel = hiltViewModel()
) {
    val adoptions = listingViewModel.uiAdoptions.collectAsState().value
    val showAll by listingViewModel.showAll
    var filterText by rememberSaveable { mutableStateOf("") }
    val filteredAdoptions = adoptions.filter {
        it.petBreed.contains(filterText, ignoreCase = true)
    }
    val isError = listingViewModel.isErr.value
    val isLoading = listingViewModel.isLoading.value
    val error = listingViewModel.error.value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ListingText()

            Text("Show All")
            Switch(
                checked = showAll,
                onCheckedChange = { listingViewModel.toggleShowAll() }
            )
        }

        TextField(
            value = filterText,
            onValueChange = { value -> filterText = value },
            label = { Text("Search Breed") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        if (isLoading) {
            Centre(Modifier.fillMaxSize()) {
                ShowLoader("Loading Adoptions...")
            }
            return
        }

        if (adoptions.isEmpty() && !isError) {
            Centre(Modifier.fillMaxSize()) {
                Text(
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    lineHeight = 34.sp,
                    textAlign = TextAlign.Center,
                    text = stringResource(R.string.empty_list)
                )
            }
            return
        }

        if (isError) {
            ShowError(
                headline = error.message ?: "Unknown error",
                subtitle = error.toString(),
                onClick = { listingViewModel.getAdoptions() }
            )
            return
        }

        AdoptionCardList(
            adoptions = filteredAdoptions,
            onClickAdoptionDetails = onClickAdoptionDetails,
            onDeleteAdoption = { adoption: AdoptionModel ->
                listingViewModel.deleteAdoption(adoption)
            },
        )
    }
}