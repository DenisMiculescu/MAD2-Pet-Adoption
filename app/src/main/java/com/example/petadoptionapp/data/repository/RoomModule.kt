package com.example.petadoptionapp.data.repository

import android.content.Context
import androidx.room.Room
import com.example.petadoptionapp.data.room.AdoptionDAO
import com.example.petadoptionapp.data.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context):
            AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "pet_adoption_database")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideAdoptionDAO(appDatabase: AppDatabase):
            AdoptionDAO = appDatabase.getAdoptionDAO()

    @Provides
    fun provideRoomRepository(adoptionDAO: AdoptionDAO):
            RoomRepository = RoomRepository(adoptionDAO)
}
