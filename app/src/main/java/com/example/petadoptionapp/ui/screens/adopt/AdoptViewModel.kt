package com.example.petadoptionapp.ui.screens.adopt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petadoptionapp.data.AdoptionModel
import com.example.petadoptionapp.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdoptViewModel @Inject
constructor(private val repository: RoomRepository) : ViewModel() {

    fun insert(adoptions: AdoptionModel)
            = viewModelScope.launch {
        repository.insert(adoptions)
    }
}
