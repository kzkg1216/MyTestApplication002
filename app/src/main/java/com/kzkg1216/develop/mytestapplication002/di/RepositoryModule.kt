package com.kzkg1216.develop.mytestapplication002.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.kzkg1216.develop.mytestapplication002.data.SettingsRepositoryImpl
import com.kzkg1216.develop.mytestapplication002.domain.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideSettingsRepository(
        dataStore: DataStore<Preferences>
    ): SettingsRepository {
        return SettingsRepositoryImpl(dataStore)
    }
}