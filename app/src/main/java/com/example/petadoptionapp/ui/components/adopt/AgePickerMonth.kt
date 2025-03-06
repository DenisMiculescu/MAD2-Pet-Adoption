package com.example.petadoptionapp.ui.components.adopt

import com.chargemap.compose.numberpicker.ListItemPicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun AgePickerMonth(
    onMonthChange: (Int) -> Unit
) {
    val possibleMonths = listOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11")
    var pickerValue by remember { mutableStateOf(possibleMonths[0]) }

    ListItemPicker(
        label = { it },
        dividersColor = MaterialTheme.colorScheme.primary,
        textStyle = TextStyle(Color.Black,20.sp),
        value = pickerValue,
        onValueChange = {
            pickerValue = it
            onMonthChange(pickerValue.toInt())
        },
        list = possibleMonths
    )
}

@Preview(showBackground = true)
@Composable
fun AgePickerMonthPreview() {
    PetAdoptionAppTheme {
        AgePickerMonth(onMonthChange = {})
    }
}
