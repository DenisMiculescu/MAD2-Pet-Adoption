package com.example.petadoptionapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.petadoptionapp.ui.screens.listing.ListingScreen
import com.example.petadoptionapp.ui.screens.about.AboutScreen
import com.example.petadoptionapp.ui.screens.adopt.AdoptScreen
import com.example.petadoptionapp.ui.screens.details.DetailsScreen
import com.example.petadoptionapp.ui.screens.home.HomeScreen
import com.example.petadoptionapp.ui.screens.login.LoginScreen
import com.example.petadoptionapp.ui.screens.map.MapScreen
import com.example.petadoptionapp.ui.screens.profile.ProfileScreen
import com.example.petadoptionapp.ui.screens.register.RegisterScreen

@Composable
fun NavHostProvider(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: AppDestination,
    paddingValues: PaddingValues,
    permissions: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = startDestination.route,
        modifier = Modifier.padding(paddingValues = paddingValues)) {

        composable(route = Adopt.route) {
            //call our 'Adopt' Screen Here
            AdoptScreen(modifier = modifier, navController = navController)
        }

        composable(route = Home.route) {
            //call our 'Home' Screen Here
            HomeScreen(modifier = modifier)
        }

        composable(route = Listing.route) {
            //call our 'Listing' Screen Here
            ListingScreen(
                modifier = modifier,
                onClickAdoptionDetails = {
                        adoptionId : String ->
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
            val id = navBackStackEntry.arguments?.getString(Details.idArg)
            if (id != null) {
                DetailsScreen(navController = navController)
            }
        }

        composable(route = Login.route) {
            //call our 'Login' Screen Here
            LoginScreen(
                navController = navController,
                onLogin = { navController.popBackStack() }
            )
        }

        composable(route = Register.route) {
            //call our 'Register' Screen Here
            RegisterScreen(
                navController = navController,
                onRegister = { navController.popBackStack() }
            )
        }

        composable(route = Profile.route) {
            ProfileScreen(
                onSignOut = {
                    navController.popBackStack()
                    navController.navigate(Login.route) {
                        popUpTo(Home.route) { inclusive = true }
                    }
                },
            )
        }

        composable(route = Map.route) {
            MapScreen(permissions = permissions)
        }

    }
}

private fun NavHostController.navigateToAdoptionDetails(adoptionId: String) {
    this.navigate("details/$adoptionId")
}

