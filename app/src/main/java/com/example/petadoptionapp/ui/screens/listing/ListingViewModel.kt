package com.example.petadoptionapp.ui.screens.listing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {
    private val _adoptions
            = MutableStateFlow<List<AdoptionModel>>(emptyList())
    val uiAdoptions: StateFlow<List<AdoptionModel>>
            = _adoptions.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAll().collect { listOfAdoptions ->
                _adoptions.value = listOfAdoptions
            }
        }
    }
}
