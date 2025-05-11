package com.example.petadoptionapp.ui.screens.listing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petadoptionapp.data.model.AdoptionModel
import com.example.petadoptionapp.firebase.database.FirestoreRepository
import com.example.petadoptionapp.firebase.services.AuthService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject
constructor(
    private val repository: FirestoreRepository,
    private val authService: AuthService
) : ViewModel() {

    private val _adoptions = MutableStateFlow<List<AdoptionModel>>(emptyList())
    val uiAdoptions: StateFlow<List<AdoptionModel>> = _adoptions.asStateFlow()
    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

    var showAll = mutableStateOf(false)

    fun toggleShowAll() {
        showAll.value = !showAll.value
        getAdoptions()
    }

    init { getAdoptions() }

    fun getAdoptions() {
        viewModelScope.launch {
            try {
                isLoading.value = true

                val result = if (showAll.value) {
                    repository.getAllAdoptions().first()
                } else {
                    repository.getAll(authService.email!!).first()
                }

                _adoptions.value = result
                isErr.value = false

            } catch (e: Exception) {
                Timber.e(e)
                isErr.value = true
                error.value = e
            } finally {
                isLoading.value = false
            }
        }
    }



    fun deleteAdoption(adoption: AdoptionModel)
        = viewModelScope.launch {
            repository.delete(authService.email!!,adoption._id)
            getAdoptions()
    }
}
