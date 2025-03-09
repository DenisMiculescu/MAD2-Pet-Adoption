package com.example.petadoptionapp.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.petadoptionapp.ui.components.details.DetailsScreenText
import com.example.petadoptionapp.ui.components.details.ReadOnlyTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailsViewModel = hiltViewModel(),
    navController: NavController,
    ) {
    var adoption = detailViewModel.adoption.value

    val errorEmptyMessage = "Message Cannot be Empty..."
    val errorShortMessage = "Message must be at least 2 characters"
    var textPetName by rememberSaveable { mutableStateOf("") }
    var textAgeYear by rememberSaveable { mutableStateOf("") }
    var textOwnerName by rememberSaveable { mutableStateOf("") }
    var textOwnerContact by rememberSaveable { mutableStateOf("") }
    var onMessageChanged by rememberSaveable { mutableStateOf(false) }
    var isEmptyError by rememberSaveable { mutableStateOf(false) }
    var isShortError by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        isEmptyError = text.isEmpty()
        isShortError = text.length < 2
        onMessageChanged = !(isEmptyError || isShortError)
    }

    Column(modifier = modifier.padding(
        start = 24.dp,
        end = 24.dp,
    ),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        DetailsScreenText()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(
                start = 10.dp,
                end = 10.dp,
            ),
        )
        {
            //Pet Name Field
            textPetName = adoption.petName
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = textPetName,
                onValueChange = {
                    textPetName = it
                    validate(textPetName)
                    adoption.petName = textPetName
                },
                maxLines = 2,
                label = { Text(text = "Pet Name") },
                isError = isEmptyError || isShortError,
                supportingText = {
                    if (isEmptyError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmptyMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    else
                        if (isShortError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = errorShortMessage,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                },
                trailingIcon = {
                    if (isEmptyError || isShortError)
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                    else
                        Icon(
                            Icons.Default.Edit, contentDescription = "add/edit",
                            tint = Color.Black
                        )
                },
                keyboardActions = KeyboardActions { validate(textPetName) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                )
            )

            //Pet Breed Field
            ReadOnlyTextField(value = adoption.petBreed,
                label = "Pet Breed")

            //Pet Name Field
            textAgeYear = adoption.ageYear.toString()
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = textAgeYear,
                onValueChange = {
                    textAgeYear = it
                    validate(textAgeYear)
                    adoption.ageYear = textAgeYear.toInt()
                },
                maxLines = 2,
                label = { Text(text = "Pet Age") },
                isError = isEmptyError || isShortError,
                supportingText = {
                    if (isEmptyError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmptyMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    else
                        if (isShortError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = errorShortMessage,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                },
                trailingIcon = {
                    if (isEmptyError || isShortError)
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                    else
                        Icon(
                            Icons.Default.Edit, contentDescription = "add/edit",
                            tint = Color.Black
                        )
                },
                keyboardActions = KeyboardActions { validate(textAgeYear) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                )
            )

            //Owner Name Field
            textOwnerName = adoption.ownerName
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = textOwnerName,
                onValueChange = {
                    textOwnerName = it
                    validate(textOwnerName)
                    adoption.ownerName = textOwnerName
                },
                maxLines = 2,
                label = { Text(text = "Owner Name") },
                isError = isEmptyError || isShortError,
                supportingText = {
                    if (isEmptyError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmptyMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    else
                        if (isShortError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = errorShortMessage,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                },
                trailingIcon = {
                    if (isEmptyError || isShortError)
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                    else
                        Icon(
                            Icons.Default.Edit, contentDescription = "add/edit",
                            tint = Color.Black
                        )
                },
                keyboardActions = KeyboardActions { validate(textOwnerName) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                )
            )

            //Owner Contact Field
            textOwnerContact = adoption.ownerContact
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = textOwnerContact,
                onValueChange = {
                    textOwnerContact = it
                    validate(textOwnerContact)
                    adoption.ownerContact = textOwnerContact
                },
                maxLines = 2,
                label = { Text(text = "Owner Contact Number") },
                isError = isEmptyError || isShortError,
                supportingText = {
                    if (isEmptyError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = errorEmptyMessage,
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                    else
                        if (isShortError) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = errorShortMessage,
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                },
                trailingIcon = {
                    if (isEmptyError || isShortError)
                        Icon(Icons.Filled.Warning,"error", tint = MaterialTheme.colorScheme.error)
                    else
                        Icon(
                            Icons.Default.Edit, contentDescription = "add/edit",
                            tint = Color.Black
                        )
                },
                keyboardActions = KeyboardActions { validate(textOwnerContact) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                )
            )

            // Location Field
            ReadOnlyTextField(value = adoption.location,
                label = "Location")
            // Date Listed Field
            ReadOnlyTextField(value = adoption.dateListed.toString(),
                label = "Date Listed")


            Spacer(modifier.height(height = 48.dp))
            Button(
                onClick = {
                    detailViewModel.updateAdoption(adoption)
                    navController.navigate("listing")
                    onMessageChanged = false},
                elevation = ButtonDefaults.buttonElevation(20.dp),
                enabled = onMessageChanged,
            ){
                Icon(Icons.Default.Save, contentDescription = "Save")
                Spacer(modifier.width(width = 8.dp))
                Text(
                    text = "Save",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        }
    }
}

