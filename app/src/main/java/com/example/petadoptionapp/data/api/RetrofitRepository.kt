package com.example.petadoptionapp.data.api

import com.example.petadoptionapp.data.model.AdoptionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject
constructor(private val serviceApi: AdoptionService)  {

    suspend fun getAll(email: String): List<AdoptionModel>
    {
        return withContext(Dispatchers.IO) {
            val adoptions = serviceApi.getall(email)
            adoptions.body() ?: emptyList()
        }
    }

    suspend fun get(email: String, id: String): AdoptionModel
    {
        return withContext(Dispatchers.IO) {
            val adoption = serviceApi.get(email,id)
            adoption.body()!!
        }
    }

    suspend fun insert(email: String, adoption: AdoptionModel) : AdoptionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.post(email, adoption)
            wrapper
        }
    }

    suspend fun update(email: String, adoption: AdoptionModel) : AdoptionWrapper
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.put(email, adoption._id, adoption)
            wrapper
        }
    }

    suspend fun delete(email: String, adoption: AdoptionModel) : Response<Void>
    {
        return withContext(Dispatchers.IO) {
            val wrapper = serviceApi.delete(email, adoption._id)
            wrapper
        }
    }
}
