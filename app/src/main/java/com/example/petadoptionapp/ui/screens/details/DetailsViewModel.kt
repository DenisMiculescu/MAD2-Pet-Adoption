package com.example.petadoptionapp.ui.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(private val repository: RoomRepository,
            savedStateHandle: SavedStateHandle) : ViewModel() {

    var adoption = mutableStateOf(
        AdoptionModel(
            petName = "",
            petType = "",
            petBreed = "",
            ageYear = 0,
            chipped = "",
            location = "",
            dateListed = Date(),
            ownerName = "",
            ownerContact = ""
        )
    )

    val id: Int = checkNotNull(savedStateHandle["id"])

    init {
        viewModelScope.launch {
            repository.get(id).collect { objAdoption ->
                adoption.value = objAdoption
            }
        }
    }

    fun updateAdoption(adoption: AdoptionModel) {
        viewModelScope.launch { repository.update(adoption) }
    }
}
