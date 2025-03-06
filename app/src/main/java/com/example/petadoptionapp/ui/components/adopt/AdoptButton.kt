package com.example.petadoptionapp.ui.components.adopt

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
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
import com.example.petadoptionapp.R
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.data.fakeAdoptions
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme
import timber.log.Timber
import java.util.Date


@Composable
fun AdoptButton(
    modifier: Modifier = Modifier,
    adoption: AdoptionModel,
    adoptions: SnapshotStateList<AdoptionModel>,
) {

    val context = LocalContext.current

    Row(modifier = modifier) {
        Button(
            onClick = {
                adoptions.add(adoption)
                Timber.i("Adoption info : $adoptions")
                Timber.i("Adoption List info : ${adoptions.toList()}")
            },
            elevation = ButtonDefaults.buttonElevation(20.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Adopt Pet")
            Spacer(modifier.width(4.dp))
            Text(
                text = stringResource(R.string.adoptButton),
                fontWeight = FontWeight.Bold,
                fontSize = 10.sp,
                color = Color.White
            )
        }

        Spacer(modifier.weight(1f))

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
}

@Preview(showBackground = true)
@Composable
fun AdoptButtonPreview() {
    PetAdoptionAppTheme {
        AdoptButton(
            Modifier,
            AdoptionModel(
                petName = "Buddy",
                petType = "Dog",
                petBreed = "Golden Retriever",
                ageYear = 2,
                ageMonth = 5,
                chipped = true,
                location = "Dublin",
                dateListed = Date(),
            ),
            adoptions = fakeAdoptions.toMutableStateList()
        )
    }
}
