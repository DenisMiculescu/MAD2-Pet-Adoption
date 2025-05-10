package com.example.petadoptionapp.ui.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petadoptionapp.data.model.AdoptionModel
import com.example.petadoptionapp.firebase.database.FirestoreRepository
import com.example.petadoptionapp.firebase.services.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(
    private val repository: FirestoreRepository,
    private val authService: AuthService,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var adoption = mutableStateOf(AdoptionModel())
    val id: String = checkNotNull(savedStateHandle["id"])
    var isErr = mutableStateOf(false)
    var error = mutableStateOf(Exception())
    var isLoading = mutableStateOf(false)

    init {
        viewModelScope.launch {
            try {
                isLoading.value = true
                adoption.value = repository.get(authService.email!!,id)!!
                isErr.value = false
                isLoading.value = false
            } catch (e: Exception) {
                isErr.value = true
                error.value = e
                isLoading.value = false
            }
        }
    }

    fun updateAdoption(adoption: AdoptionModel) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                repository.update(authService.email!!, adoption)
                isErr.value = false
                isLoading.value = false
            } catch (e: Exception) {
                isErr.value = true
                error.value = e
                isLoading.value = false
            }
        }
    }
}

