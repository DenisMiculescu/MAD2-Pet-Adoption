package com.example.petadoptionapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.data.fakeAdoptions
import com.example.petadoptionapp.ui.components.report.AdoptionCardList
import com.example.petadoptionapp.ui.components.report.ReportText
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme

@Composable
fun ScreenReport(modifier: Modifier = Modifier,
                 adoptions: SnapshotStateList<AdoptionModel>
) {

    Column {
        Column(
            modifier = modifier.padding(
                top = 48.dp,
                start = 24.dp,
                end = 24.dp
            ),
        ) {
            ReportText()
            AdoptionCardList(
                adoptions = adoptions
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReportScreenPreview() {
    PetAdoptionAppTheme {
        ScreenReport( modifier = Modifier,
            adoptions = fakeAdoptions.toMutableStateList()
        )
    }
}
