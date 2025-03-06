package com.example.petadoptionapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petadoptionapp.R
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.data.fakeAdoptions
import com.example.petadoptionapp.ui.components.general.Centre
import com.example.petadoptionapp.ui.components.listing.AdoptionCardList
import com.example.petadoptionapp.ui.components.listing.ListingText
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme

@Composable
fun ScreenListing(modifier: Modifier = Modifier,
                 adoptions: SnapshotStateList<AdoptionModel>
) {

    Column {
        Column(
            modifier = modifier.padding(
                start = 24.dp,
                end = 24.dp
            ),
        ) {
            ListingText()
            if(adoptions.isEmpty())
                Centre(Modifier.fillMaxSize()) {
                    Text(color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.empty_list)
                    )
                }
            else
                AdoptionCardList(
                    adoptions = adoptions
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListingScreenPreview() {
    PetAdoptionAppTheme {
        ScreenListing( modifier = Modifier,
            adoptions = fakeAdoptions.toMutableStateList()
        )
    }
}
