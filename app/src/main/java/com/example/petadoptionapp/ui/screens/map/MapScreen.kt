package com.example.petadoptionapp.ui.screens.map

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.petadoptionapp.R
import com.example.petadoptionapp.ui.screens.listing.ListingViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapScreen(
    mapViewModel: MapViewModel = hiltViewModel(),
    listingViewModel: ListingViewModel = hiltViewModel(),
    permissions: Boolean
) {
    val uiSettings = remember {
        MapUiSettings(
            myLocationButtonEnabled = true,
            compassEnabled = true,
            mapToolbarEnabled = true
        )
    }
    val properties = remember {
        MapProperties(
            mapType = MapType.NORMAL,
            isMyLocationEnabled = true
        )
    }

    val context = LocalContext.current
    val currentLocation by mapViewModel.currentLatLng.collectAsState()
    val cameraPositionState = rememberCameraPositionState()
    val adoptions by listingViewModel.uiAdoptions.collectAsState()
    var adoptionIcon by remember { mutableStateOf<BitmapDescriptor?>(null) }

    LaunchedEffect(Unit) {
        val drawable = ContextCompat.getDrawable(context, R.drawable.adopt_marker)
        drawable?.let {
            val bitmap = Bitmap.createBitmap(150, 150, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            it.setBounds(0, 0, canvas.width, canvas.height)
            it.draw(canvas)
            adoptionIcon = BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    if(permissions) {
        LaunchedEffect(currentLocation) {
            mapViewModel.getLocationUpdates()
            cameraPositionState.animate(CameraUpdateFactory.newLatLng(currentLocation))
            cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation, 15f)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = uiSettings,
            properties = properties
        ) {
            Marker(
                state = MarkerState(position = currentLocation),
                title = "You",
                snippet = "Your current location"
            )
            adoptions.forEach {
                val position = LatLng(it.latitude, it.longitude)
                Marker(
                    state = MarkerState(position = position),
                    title = "${it.petName}, ${it.petType}, ${it.petBreed}",
                    snippet = "${it.ownerName}, ${it.ownerContact}",
                    icon = adoptionIcon
                )
            }
        }
    }
}
