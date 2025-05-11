package com.example.petadoptionapp.ui.components.adopt

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.petadoptionapp.R
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme
import timber.log.Timber

@Composable
fun RadioButtonGroup(modifier: Modifier = Modifier,
                     onPaymentTypeChange: (String) -> Unit) {

    val radioOptions = listOf(
        stringResource(R.string.chipped_true),
        stringResource(R.string.chipped_false)
    )
    var chipped by remember { mutableStateOf(radioOptions[0]) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ){
        radioOptions.forEach { chippedText ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (chippedText == chipped),
                    onClick = {
                        chipped = chippedText
                        onPaymentTypeChange(chipped)
                    }
                )
                Text(
                    text = chippedText,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}