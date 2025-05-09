package com.example.petadoptionapp.ui.screens.listing

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petadoptionapp.data.model.AdoptionModel
import com.example.petadoptionapp.data.api.RetrofitRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject
constructor(private val repository: RetrofitRepository) : ViewModel() {
    private val _adoptions
            = MutableStateFlow<List<AdoptionModel>>(emptyList())
    val uiAdoptions: StateFlow<List<AdoptionModel>>
            = _adoptions.asStateFlow()
    var isErr = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var error = mutableStateOf(Exception())

    init { getAdoptions() }

    fun getAdoptions() {
        viewModelScope.launch {
            try {
                isLoading.value = true
                _adoptions.value = repository.getAll()
                isErr.value = false
                isLoading.value = false
            }
            catch(e:Exception) {
                isErr.value = true
                isLoading.value = false
                error.value = e
                Timber.i("RVM Error ${e.message}")
            }
        }
    }

    fun deleteAdoption(adoption: AdoptionModel) {
        viewModelScope.launch {
            repository.delete(adoption)
        }
    }
}
