package com.rfcreations.usaphonenumbergenerator.ui.theme

import com.rfcreations.usaphonenumbergenerator.repository.UserPreferenceRepository
import com.rfcreations.usaphonenumbergenerator.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ThemeUiState @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository
) {
    private val prefKeys = Constants.PrefKeys
    private val _selectedTheme =
        MutableStateFlow(
            userPreferenceRepository.getIntPref(prefKeys.SELECTED_THEME_KEY, 2)
        )
    val selectedTheme = _selectedTheme.asStateFlow()

    private val _darkTheme = MutableStateFlow(false)
    val darkTheme = _darkTheme.asStateFlow()

    private val _dynamicTheme = MutableStateFlow(false)
    val dynamicTheme = _dynamicTheme.asStateFlow()

    fun updateSelectedTheme(newThemeValue: Int) {
        _selectedTheme.value = newThemeValue
        userPreferenceRepository.editIntPref(prefKeys.SELECTED_THEME_KEY, newThemeValue)
    }

    fun toggleDynamicTheme() {
        _dynamicTheme.value = !_dynamicTheme.value
        userPreferenceRepository.editBooleanPref(prefKeys.DYNAMIC_THEME_KEY, _dynamicTheme.value)
    }

    fun toggleDarkThemeValue(newThemeValue: Boolean) {
        _darkTheme.value = newThemeValue
    }
}