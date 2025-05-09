package com.example.petadoptionapp.data.api

import com.example.petadoptionapp.data.AdoptionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RetrofitRepository @Inject
constructor(private val serviceApi: AdoptionService)  {

    suspend fun getAll(): List<AdoptionModel>
    {
        return withContext(Dispatchers.IO) {
            val adoptions = serviceApi.getall()
            adoptions.body() ?: emptyList()
        }
    }

    suspend fun get(id: String): List<AdoptionModel>
    {
        return withContext(Dispatchers.IO) {
            val adoption = serviceApi.get(id)
            adoption.body() ?: emptyList()
        }
    }

    suspend fun insert(adoption: AdoptionModel) : AdoptionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(adoption)
            wrapper
        }
    }

    suspend fun update(adoption: AdoptionModel) : AdoptionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(adoption._id,adoption)
            wrapper
        }
    }

    suspend fun delete(adoption: AdoptionModel) : AdoptionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(adoption._id)
            wrapper
        }
    }
}
