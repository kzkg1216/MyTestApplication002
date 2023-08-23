package com.kzkg1216.develop.mytestapplication002.di

import com.kzkg1216.develop.mytestapplication002.domain.EditDebugSettingsUseCase
import com.kzkg1216.develop.mytestapplication002.domain.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideEditSettingsUseCase(settingsRepository: SettingsRepository): EditDebugSettingsUseCase {
        return EditDebugSettingsUseCase(settingsRepository)
    }
}