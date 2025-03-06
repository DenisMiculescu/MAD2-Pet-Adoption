package com.example.petadoptionapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.data.fakeAdoptions
import com.example.petadoptionapp.ui.components.adopt.AdoptButton
import com.example.petadoptionapp.ui.components.adopt.AgePickerMonth
import com.example.petadoptionapp.ui.components.adopt.AgePickerYear
import com.example.petadoptionapp.ui.components.adopt.BreedInput
import com.example.petadoptionapp.ui.components.adopt.NameInput
import com.example.petadoptionapp.ui.components.adopt.RadioButtonGroup
import com.example.petadoptionapp.ui.components.adopt.WelcomeText
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme
import java.util.Date

@Composable
fun ScreenAdopt(
    modifier: Modifier = Modifier,
    adoptions: SnapshotStateList<AdoptionModel>
) {
    var petName by remember { mutableStateOf("") }
    var petBreed by remember { mutableStateOf("") }
    var petType by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var chipped by remember { mutableStateOf("") }

    var ageYear by remember { mutableIntStateOf(0) }
    var ageMonth by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WelcomeText()

        Text(text = "Age", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AgePickerYear(onYearChange = { ageYear = it })
            AgePickerMonth(onMonthChange = { ageMonth = it })
        }

        Text(text = "Chipped", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        RadioButtonGroup(
            modifier = modifier,
            onPaymentTypeChange = { chipped = it }
        )

        Text(text = "Pet Name", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        NameInput(name = petName, onNameChange = { petName = it })

        Text(text = "Pet Breed", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        BreedInput(breed = petBreed, onBreedChange = { petBreed = it })

        Spacer(modifier = Modifier.weight(1f))

        AdoptButton(
            modifier = modifier,
            adoption = AdoptionModel(
                petName = petName,
                petType = "Dog",
                petBreed = petBreed,
                ageYear = ageYear,
                ageMonth = ageMonth,
                chipped = true,
                location = "City Center",
                dateListed = Date(),
            ),
            adoptions = adoptions,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AdoptScreenPreview() {
    PetAdoptionAppTheme {
        ScreenAdopt(
            modifier = Modifier,
            adoptions = fakeAdoptions.toMutableStateList()
        )
    }
}
