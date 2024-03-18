package com.rfcreations.usaphonenumbergenerator.di

import android.content.Context
import android.content.SharedPreferences
import com.rfcreations.usaphonenumbergenerator.repository.UserPreferenceRepository
import com.rfcreations.usaphonenumbergenerator.repository.UserPreferenceRepositoryImpl
import com.rfcreations.usaphonenumbergenerator.ui.theme.ThemeUiState
import com.rfcreations.usaphonenumbergenerator.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        val prefName = Constants.PrefKeys.PREF_NAME
        return context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesUserPreferenceRepository(sharedPreferences: SharedPreferences): UserPreferenceRepository {
        return UserPreferenceRepositoryImpl(sharedPreferences)
    }

    @Singleton
    @Provides
    fun providesThemeUiState(userPreferenceRepository: UserPreferenceRepository
    ): ThemeUiState = ThemeUiState(userPreferenceRepository)

}