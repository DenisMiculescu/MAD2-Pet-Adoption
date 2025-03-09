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
import com.example.petadoptionapp.ui.components.details.DetailsScreenText
import com.example.petadoptionapp.ui.components.details.ReadOnlyTextField

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    detailViewModel: DetailsViewModel = hiltViewModel()
) {
    var adoption = detailViewModel.adoption.value

    val errorEmptyMessage = "Message Cannot be Empty..."
    val errorShortMessage = "Message must be at least 2 characters"
    var text by rememberSaveable { mutableStateOf("") }
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

            //Pet Breed Field
            ReadOnlyTextField(value = adoption.petBreed,
                label = "Pet Breed")
            //Age (Years) Field
            ReadOnlyTextField(value = adoption.ageYear.toString(),
                label = "Age (Years)")
            // Date Listed Field
            ReadOnlyTextField(value = adoption.dateListed.toString(),
                label = "Date Listed")
            //Pet Name Field
            text = adoption.petName
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                    validate(text)
                    adoption.petName = text
                },
                maxLines = 2,
                label = { Text(text = "Message") },
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
                keyboardActions = KeyboardActions { validate(text) },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                )
            )

            Spacer(modifier.height(height = 48.dp))
            Button(
                onClick = {
                    detailViewModel.updateAdoption(adoption)
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

