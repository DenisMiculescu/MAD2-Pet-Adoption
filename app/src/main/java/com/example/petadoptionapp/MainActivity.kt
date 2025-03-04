package com.example.petadoptionapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.ui.screens.ScreenAdopt
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PetAdoptionAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PetAdoptionApp(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun PetAdoptionApp(modifier: Modifier = Modifier) {
    val adoptions = remember { mutableStateListOf<AdoptionModel>() }

    ScreenAdopt(modifier = modifier, adoptions = adoptions)
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    PetAdoptionAppTheme {
        PetAdoptionApp(modifier = Modifier)
    }
}
