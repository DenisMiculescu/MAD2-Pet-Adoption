package com.example.petadoptionapp.ui.screens.adopt

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.petadoptionapp.data.model.AdoptionModel
import com.example.petadoptionapp.data.model.fakeAdoptions
import com.example.petadoptionapp.ui.components.adopt.AdoptButton
import com.example.petadoptionapp.ui.components.adopt.AgePickerYear
import com.example.petadoptionapp.ui.components.adopt.BreedInput
import com.example.petadoptionapp.ui.components.adopt.LocationInput
import com.example.petadoptionapp.ui.components.adopt.NameInput
import com.example.petadoptionapp.ui.components.adopt.OwnerContact
import com.example.petadoptionapp.ui.components.adopt.OwnerNameInput
import com.example.petadoptionapp.ui.components.adopt.PetTypeTabs
import com.example.petadoptionapp.ui.components.adopt.RadioButtonGroup
import com.example.petadoptionapp.ui.components.adopt.WelcomeText
import com.example.petadoptionapp.ui.screens.listing.ListingViewModel
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme
import java.util.Date

@Composable
fun AdoptScreen(
    modifier: Modifier = Modifier,
    listingViewModel: ListingViewModel = hiltViewModel(),
    navController: NavController
) {
    val adoptions = listingViewModel.uiAdoptions.collectAsState().value

    var petName by remember { mutableStateOf("") }
    var petBreed by remember { mutableStateOf("") }
    var petType by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var chipped by remember { mutableStateOf("") }
    var ageYear by remember { mutableIntStateOf(0) }
    var ownerName by remember { mutableStateOf("") }
    var ownerContact by remember { mutableStateOf("") }

    val cardBorderColor = if (petType == "Dog") Color.Blue else Color.Red

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        WelcomeText()

        Box(modifier = Modifier.fillMaxWidth()) {
            PetTypeTabs(
                onPetTypeChange = { petType = it },
                selectedPetType = petType
            )
        }

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = (-8).dp),
            shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 12.dp, bottomEnd = 12.dp),
            border = BorderStroke(2.dp, cardBorderColor),
            elevation = CardDefaults.elevatedCardElevation(4.dp)
        ) {
            Column (
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Add a $petType for Adoption",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = cardBorderColor
                )

                Text(text = "Name", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                NameInput(name = petName, onNameChange = { petName = it })

                Text(text = "Breed", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                BreedInput(breed = petBreed, onBreedChange = { petBreed = it })

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(text = "Age", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    AgePickerYear(onYearChange = { ageYear = it })
                }

                Text(text = "Chipped", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                RadioButtonGroup(
                    modifier = modifier,
                    onPaymentTypeChange = { chipped = it }
                )

                Text(text = "Owner Details", fontWeight = FontWeight.Bold, fontSize = 25.sp)

                Text(text = "Owner Name", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                OwnerNameInput(ownerName = ownerName, onOwnerNameChange = { ownerName = it })

                Text(text = "Owner Contact", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                OwnerContact(ownerContact = ownerContact, onOwnerContactChange = { ownerContact = it })

                Text(text = "Location", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                LocationInput(location = location, onLocationChange = { location = it })
            }
        }

        AdoptButton(
            modifier = modifier,
            adoption = AdoptionModel(
                petName = petName,
                petType = "Dog",
                petBreed = petBreed,
                ageYear = ageYear,
                chipped = chipped,
                location = location,
                dateListed = Date(),
                ownerName = ownerName,
                ownerContact = ownerContact
            ),
            navController = navController
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AdoptScreenPreview() {
    PetAdoptionAppTheme {
        PreviewAdoptScreen( modifier = Modifier,
            adoptions = fakeAdoptions.toMutableStateList())
    }
}

@Composable
fun PreviewAdoptScreen(modifier: Modifier = Modifier,
                       adoptions: SnapshotStateList<AdoptionModel>
) {
    var petName by remember { mutableStateOf("") }
    var petBreed by remember { mutableStateOf("") }
    var petType by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var chipped by remember { mutableStateOf("") }

    var ageYear by remember { mutableIntStateOf(0) }

    Column(
        modifier = modifier.padding(
            start = 24.dp,
            end = 24.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WelcomeText()

        Text(text = "Age", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AgePickerYear(onYearChange = { ageYear = it })
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
                petType = petType,
                petBreed = petBreed,
                ageYear = ageYear,
                chipped = chipped,
                location = "City Center",
                dateListed = Date(),
                ownerName = "John Doe",
                ownerContact = "1234567890"
            ),
            navController = NavController(LocalContext.current)
        )
    }
}

