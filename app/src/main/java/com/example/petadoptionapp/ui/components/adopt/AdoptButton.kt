package com.example.petadoptionapp.ui.components.adopt

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.petadoptionapp.R
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.ui.components.general.ShowLoader
import com.example.petadoptionapp.ui.screens.adopt.AdoptViewModel
import com.example.petadoptionapp.ui.screens.listing.ListingViewModel
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme
import timber.log.Timber
import java.util.Date


@Composable
fun AdoptButton(
    modifier: Modifier = Modifier,
    adoption: AdoptionModel,
    navController: NavController,
    adoptViewModel: AdoptViewModel = hiltViewModel(),
    listingViewModel: ListingViewModel = hiltViewModel(),
) {
    val adoptions = listingViewModel.uiAdoptions.collectAsState().value
    val context = LocalContext.current
    var showError by remember { mutableStateOf(false) }

    val isError = adoptViewModel.isErr.value
    val error = adoptViewModel.error.value
    val isLoading = adoptViewModel.isLoading.value

    if(isLoading) ShowLoader("Trying to add Adoption...")


    Column(modifier = modifier) {
        Row {
            Button(
                onClick = {
                    if (adoption.ownerContact.isNotEmpty() && adoption.ownerName.isNotEmpty()) {
                        adoptViewModel.insert(adoption)
                        Timber.i("Adoption info : $adoptions")
                        Timber.i("Adoption List info : ${adoptions.toList()}")
                        navController.navigate("listing")
                    } else {
                        showError = true
                    }
                },
                elevation = ButtonDefaults.buttonElevation(20.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adopt Pet")
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = stringResource(R.string.adoptButton),
                    fontWeight = FontWeight.Bold,
                    fontSize = 10.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black
                        )
                    ) {
                        append(stringResource(R.string.totalForAdoption) + " ")
                    }

                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        append(adoptions.size.toString())
                    }
                }
            )
        }
        if (showError) {
            Text(
                text = "Please fill in all fields correctly",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        if(isError)
            Toast.makeText(context,"Unable to add Adoption at this Time...",
                Toast.LENGTH_SHORT).show()
        else
            listingViewModel.getAdoptions()
    }
}