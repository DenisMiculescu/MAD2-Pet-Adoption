package com.example.petadoptionapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.ui.components.general.MenuItem
import com.example.petadoptionapp.ui.screens.ScreenAdopt
import com.example.petadoptionapp.ui.screens.ScreenReport
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetAdoptionApp(modifier: Modifier = Modifier) {
    val adoptions = remember { mutableStateListOf<AdoptionModel>() }
    var selectedMenuItem by remember { mutableStateOf<MenuItem?>(MenuItem.Adopt) }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    if(selectedMenuItem == MenuItem.Adopt) {
                        IconButton(onClick = {
                            selectedMenuItem = MenuItem.Report
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = "Options",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                    else {
                        IconButton(onClick = {
                            selectedMenuItem = MenuItem.Adopt
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Options",
                                tint = Color.White,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            )
        },
        content = {
            when (selectedMenuItem) {
                MenuItem.Adopt -> ScreenAdopt(modifier = modifier, adoptions = adoptions)
                MenuItem.Report -> ScreenReport(modifier = modifier, adoptions = adoptions)
                else -> {}
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    PetAdoptionAppTheme {
        PetAdoptionApp(modifier = Modifier)
    }
}
