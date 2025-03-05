package com.example.petadoptionapp.main

import android.app.Application
import timber.log.Timber

class PetAdoptionMainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Starting Pet Adoption Application")
    }
}
