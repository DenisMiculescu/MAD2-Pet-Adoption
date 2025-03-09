package com.example.petadoptionapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import kotlin.random.Random

@Entity
data class AdoptionModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var petName: String,
    val petType: String,
    val petBreed: String,
    var ageYear: Int,
    val chipped: String,
    val location: String,
    val dateListed: Date = Date(),
    var ownerName: String,
    var ownerContact: String,
)

val fakeAdoptions = List(30) { i ->
    AdoptionModel(
        id = 12345 + i,
        petName = "Pet $i",
        petType = if (i % 2 == 0) "Dog" else "Cat",
        petBreed = if (i % 2 == 0) "German Shepard" else "Tabby",
        ageYear = Random.nextInt(0, 15),
        chipped = if (i % 2 == 0) "Yes" else "No",
        location = "City $i",
        dateListed = Date(),
        ownerName = "Owner $i",
        ownerContact = "1234567890"
    )
}
