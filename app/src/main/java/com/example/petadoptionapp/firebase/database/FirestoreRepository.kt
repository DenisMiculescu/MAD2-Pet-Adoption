package com.example.petadoptionapp.firebase.database

import android.net.Uri
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
import timber.log.Timber
import java.util.Date
import javax.inject.Inject

class FirestoreRepository
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

    override suspend fun insert(email: String, adoption: Adoption) {
        val adoptionWithEmailAndImage =
            adoption.copy(
                email = email,
                imageUri = auth.customPhotoUri!!.toString()
            )
        firestore.collection(ADOPTION_COLLECTION)
            .add(adoptionWithEmailAndImage)
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

    override suspend fun updatePhotoUris(email: String, uri: Uri) {

        firestore.collection(ADOPTION_COLLECTION)
            .whereEqualTo(USER_EMAIL, email)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Timber.i("FSR Updating ID ${document.id}")
                    firestore.collection(ADOPTION_COLLECTION)
                        .document(document.id)
                        .update("imageUri", uri.toString())
                }
            }
            .addOnFailureListener { exception ->
                Timber.i("Error $exception")
            }
    }

}
