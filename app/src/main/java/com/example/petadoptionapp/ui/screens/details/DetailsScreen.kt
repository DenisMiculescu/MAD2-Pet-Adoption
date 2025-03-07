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
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
            ReadOnlyTextField(value = adoption.petName,
                label = "Pet Name")
            //Pet Breed Field
            ReadOnlyTextField(value = adoption.petBreed,
                label = "Pet Breed")
            //Age (Years) Field
            ReadOnlyTextField(value = adoption.ageYear.toString(),
                label = "Age (Years)")
            // Age (Month) Field
            ReadOnlyTextField(value = adoption.ageMonth.toString(),
                label = "Age (Month)")
            // Date Listed Field
            ReadOnlyTextField(value = adoption.dateListed.toString(),
                label = "Date Listed")
            //Message Field

            //End of Message Field
            Spacer(modifier.height(height = 48.dp))
            Button(
                onClick = {},
                elevation = ButtonDefaults.buttonElevation(20.dp),
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
