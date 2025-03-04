package com.example.petadoptionapp.data

import java.util.Date
import kotlin.random.Random

data class AdoptionModel(
    val id: Int = Random.nextInt(1, 100000),
    val petName: String,
    val petType: String,
    val petBreed: String,
    val ageYear: Int,
    val ageMonth: Int,
    val chipped: Boolean = false,
    val location: String,
    val dateListed: Date = Date(),
)

val fakeAdoptions = List(30) { i ->
    AdoptionModel(
        id = 12345 + i,
        petName = "Pet $i",
        petType = if (i % 2 == 0) "Dog" else "Cat",
        petBreed = if (i % 2 == 0) "German Shepard" else "Tabby",
        ageYear = Random.nextInt(0, 15),
        ageMonth = Random.nextInt(0, 11),
        chipped = i % 3 == 0,
        location = "City $i",
        dateListed = Date()
    )
}
