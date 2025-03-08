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
import com.example.petadoptionapp.ui.screens.listing.ListingScreen
import com.example.petadoptionapp.ui.screens.about.AboutScreen
import com.example.petadoptionapp.ui.screens.adopt.AdoptScreen
import com.example.petadoptionapp.ui.screens.details.DetailsScreen

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
            AdoptScreen(modifier = modifier)
        }
        composable(route = Listing.route) {
            //call our 'Listing' Screen Here
            ListingScreen(modifier = modifier,
                onClickAdoptionDetails = {
                        adoptionId : Int ->
                    navController.navigateToAdoptionDetails(adoptionId)
                },
            )
        }
        composable(route = About.route) {
            //call our 'About' Screen Here
            AboutScreen(modifier = modifier)
        }
        composable(
            route = Details.route,
            arguments = Details.arguments
        )
        { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(Details.idArg)
            if (id != null) {
                DetailsScreen()
            }
        }

    }
}

private fun NavHostController.navigateToAdoptionDetails(adoptionId: Int) {
    this.navigate("details/$adoptionId")
}

