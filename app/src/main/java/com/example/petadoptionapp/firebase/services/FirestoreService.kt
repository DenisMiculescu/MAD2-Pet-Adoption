package com.example.petadoptionapp.firebase.services

import com.example.petadoptionapp.data.model.AdoptionModel
import kotlinx.coroutines.flow.Flow

typealias Adoption = AdoptionModel
typealias Adoptions = Flow<List<Adoption>>

interface FirestoreService {

    suspend fun getAll(email: String) : Adoptions
    suspend fun get(email: String, adoptionId: String) : Adoption?
    suspend fun insert(email: String, adoption: Adoption)
    suspend fun update(email: String, adoption: Adoption)
    suspend fun delete(email: String, adoptionId: String)
}
