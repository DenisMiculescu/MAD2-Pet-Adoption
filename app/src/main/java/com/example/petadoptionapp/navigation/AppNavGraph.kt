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
import com.example.petadoptionapp.ui.screens.ScreenAdopt
import com.example.petadoptionapp.ui.screens.ScreenReport


@Composable
fun NavHostProvider(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
    adoptions: SnapshotStateList<AdoptionModel>
) {
    NavHost(
        navController = navController,
        startDestination = Report.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        composable(route = Adopt.route) {
            //call our 'Adopt' Screen Here
            ScreenAdopt(modifier = modifier, adoptions = adoptions)
        }
        composable(route = Report.route) {
            //call our 'Report' Screen Here
            ScreenReport(modifier = modifier, adoptions = adoptions)
        }
    }
}
