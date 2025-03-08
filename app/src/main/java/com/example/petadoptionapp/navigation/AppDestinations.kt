package com.example.petadoptionapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface AppDestination {
    val icon: ImageVector
    val label: String
    val route: String
}

object Listing : AppDestination {
    override val icon = Icons.AutoMirrored.Filled.List
    override val label = "Listing"
    override val route = "listing"
}

object Adopt : AppDestination {
    override val icon = Icons.Filled.AddCircle
    override val label = "Adopt"
    override val route = "adopt"
}

object About : AppDestination {
    override val icon = Icons.Filled.Info
    override val label = "About"
    override val route = "about"
}

object Details : AppDestination {
    override val icon = Icons.Filled.Details
    override val label = "Details"
    const val idArg = "id"
    override val route = "details/{$idArg}"
    val arguments = listOf(
        navArgument(idArg) { type = NavType.IntType }
    )
}


val bottomAppBarDestinations = listOf(Adopt, Listing, About)
val allDestinations = listOf(Listing, Adopt, About, Details)
