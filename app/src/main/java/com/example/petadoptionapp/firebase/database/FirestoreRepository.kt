package com.example.petadoptionapp.firebase.database

import com.example.petadoptionapp.data.rules.Constants.ADOPTION_COLLECTION
import com.example.petadoptionapp.data.rules.Constants.USER_EMAIL
import com.example.petadoptionapp.firebase.services.Adoption
import com.example.petadoptionapp.firebase.services.Adoptions
import com.example.petadoptionapp.firebase.services.AuthService
import com.example.petadoptionapp.firebase.services.FirestoreService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await
import java.util.Date
import javax.inject.Inject

class   FirestoreRepository
@Inject constructor(private val auth: AuthService,
                    private val firestore: FirebaseFirestore
) : FirestoreService {

    override suspend fun getAll(email: String): Adoptions {

        return firestore.collection(ADOPTION_COLLECTION)
            .whereEqualTo(USER_EMAIL, email)
            .dataObjects()
    }

    override suspend fun get(email: String,
                             adoptionId: String): Adoption? {
        return firestore.collection(ADOPTION_COLLECTION)
            .document(adoptionId).get().await().toObject()
    }

    override suspend fun insert(email: String,
                                adoption: Adoption)
    {
        val adoptionWithEmail = adoption.copy(email = email)

        firestore.collection(ADOPTION_COLLECTION)
            .add(adoptionWithEmail)
            .await()

    }

    override suspend fun update(email: String,
                                adoption: Adoption)
    {
        val adoptionWithModifiedDate =
            adoption.copy(dateModified = Date())

        firestore.collection(ADOPTION_COLLECTION)
            .document(adoption._id)
            .set(adoptionWithModifiedDate).await()
    }

    override suspend fun delete(email: String,
                                adoptionId: String)
    {
        firestore.collection(ADOPTION_COLLECTION)
            .document(adoptionId)
            .delete().await()
    }
}
