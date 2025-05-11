package com.example.petadoptionapp.ui.screens.home

import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.petadoptionapp.navigation.Listing
import com.example.petadoptionapp.navigation.Login
import com.example.petadoptionapp.navigation.NavHostProvider
import com.example.petadoptionapp.navigation.allDestinations
import com.example.petadoptionapp.navigation.bottomAppBarDestinations
import com.example.petadoptionapp.navigation.userSignedOutDestinations
import com.example.petadoptionapp.ui.components.general.BottomAppBarProvider
import com.example.petadoptionapp.ui.components.general.TopAppBarProvider
import com.example.petadoptionapp.ui.screens.listing.ListingViewModel
import com.example.petadoptionapp.ui.screens.map.MapViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    mapViewModel: MapViewModel = hiltViewModel(),
    listingViewModel: ListingViewModel = hiltViewModel(),
) {
    val currentNavBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentNavBackStackEntry?.destination
    val currentBottomScreen =
        allDestinations.find { it.route == currentDestination?.route } ?: Login
    var startScreen = currentBottomScreen

    val currentUser = homeViewModel.currentUser
    val isActiveSession = homeViewModel.isAuthenticated()
    val userEmail = if (isActiveSession) currentUser?.email else ""
    val userName = if (isActiveSession) currentUser?.displayName else ""

    val locationPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    val userDestinations = if (!isActiveSession)
        userSignedOutDestinations
    else bottomAppBarDestinations

        if (isActiveSession)
            startScreen = Listing
            LaunchedEffect(locationPermissions.allPermissionsGranted) {
                locationPermissions.launchMultiplePermissionRequest()
                if (locationPermissions.allPermissionsGranted) {
                    mapViewModel.setPermissions(true)
                    mapViewModel.getLocationUpdates()
                }
            }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBarProvider(
            navController = navController,
            currentScreen = currentBottomScreen,
            canNavigateBack = navController.previousBackStackEntry != null,
            email = userEmail!!,
            name = userName!!,
                navigateUp = { navController.navigateUp() }
        )
        },
        content = { paddingValues ->
            NavHostProvider(
                modifier = modifier,
                navController = navController,
                startDestination = startScreen,
                paddingValues = paddingValues,
                permissions = mapViewModel
                    .hasPermissions
                    .collectAsState().value
            )
        },
        bottomBar = {
            BottomAppBarProvider(
                navController,
                currentScreen = currentBottomScreen,
                userDestinations
            )
        }
    )
}
