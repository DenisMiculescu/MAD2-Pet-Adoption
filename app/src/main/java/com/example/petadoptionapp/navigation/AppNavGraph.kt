package com.example.petadoptionapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.ui.screens.ScreenAbout
import com.example.petadoptionapp.ui.screens.ScreenAdopt
import com.example.petadoptionapp.ui.screens.ScreenListing

@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
    adoptions: SnapshotStateList<AdoptionModel>
) {
    NavHost(
        navController = navController,
        startDestination = Listing.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        composable(route = Adopt.route) {
            //call our 'Adopt' Screen Here
            ScreenAdopt(modifier = modifier, adoptions = adoptions)
        }
        composable(route = Listing.route) {
            //call our 'Listing' Screen Here
            ScreenListing(modifier = modifier, adoptions = adoptions)
        }
        composable(route = About.route) {
            //call our 'About' Screen Here
            ScreenAbout(modifier = modifier)
        }
    }
}
