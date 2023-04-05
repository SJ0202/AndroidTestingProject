package com.seongju.androidtestingproject.di

import android.app.Application
import androidx.room.Room
import com.seongju.androidtestingproject.data.database.UserDatabase
import com.seongju.androidtestingproject.data.repository.UserRepositoryImpl
import com.seongju.androidtestingproject.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserData(application: Application): UserDatabase {
        return Room.databaseBuilder(
            application,
            UserDatabase::class.java,
            "LocalDatabase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(database: UserDatabase): UserRepository {
        return UserRepositoryImpl(userDao = database.userDao)
    }

}