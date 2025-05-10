package com.example.petadoptionapp.ui.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petadoptionapp.data.model.AdoptionModel
import com.example.petadoptionapp.location.LocationService
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val locationService: LocationService
) : ViewModel() {

    private val _currentLatLng = MutableStateFlow(LatLng(0.0, 0.0))
    val currentLatLng: StateFlow<LatLng> = _currentLatLng

    private val _adoptions = MutableStateFlow<List<AdoptionModel>>(emptyList())
    val adoptions: StateFlow<List<AdoptionModel>> = _adoptions

    fun setAdoptions(adoptionList: List<AdoptionModel>) {
        _adoptions.value = adoptionList
    }

    fun getLocationUpdates() {
        viewModelScope.launch(Dispatchers.IO) {
            locationService.getLocationFlow().collect { location ->
                location?.let {
                    _currentLatLng.value = LatLng(it.latitude, it.longitude)
                }
            }
        }
    }
}
