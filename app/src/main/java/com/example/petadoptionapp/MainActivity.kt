package com.example.petadoptionapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.petadoptionapp.data.model.AdoptionModel
import com.example.petadoptionapp.navigation.Listing
import com.example.petadoptionapp.navigation.NavHostProvider
import com.example.petadoptionapp.navigation.allDestinations
import com.example.petadoptionapp.ui.components.general.BottomAppBarProvider
import com.example.petadoptionapp.ui.components.general.MenuItem
import com.example.petadoptionapp.ui.components.general.TopAppBarProvider
import com.example.petadoptionapp.ui.screens.home.HomeScreen
import com.example.petadoptionapp.ui.theme.PetAdoptionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PetAdoptionAppTheme { HomeScreen() }
        }
    }
}
