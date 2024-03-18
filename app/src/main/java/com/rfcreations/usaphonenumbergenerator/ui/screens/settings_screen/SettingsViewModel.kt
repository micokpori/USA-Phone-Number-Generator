package com.rfcreations.usaphonenumbergenerator.ui.screens.settings_screen

import androidx.lifecycle.ViewModel
import com.rfcreations.usaphonenumbergenerator.repository.UserPreferenceRepository
import com.rfcreations.usaphonenumbergenerator.ui.theme.ThemeUiState
import com.rfcreations.usaphonenumbergenerator.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userPreferenceRepository: UserPreferenceRepository,
    val themeUiState: ThemeUiState
) : ViewModel() {

    private val prefKeys = Constants.PrefKeys

    private val _showAppThemeDialog = MutableStateFlow(false)
    val showAppThemeDialog = _showAppThemeDialog.asStateFlow()

    private val _showNameFormatDialog = MutableStateFlow(false)
    val showNameFormatDialog = _showNameFormatDialog.asStateFlow()

    private val _selectedNameFormat = MutableStateFlow(
        userPreferenceRepository.getIntPref(prefKeys.NAME_FORMAT, 0) //Use phone number as name
    )
    val selectedNameFormat = _selectedNameFormat.asStateFlow()

    fun uiEvent(event: SettingsUiEvent) {
        when (event) {
            is SettingsUiEvent.ToggleShowAppThemeDialog -> {
                _showAppThemeDialog.value = !_showAppThemeDialog.value
            }
            is SettingsUiEvent.UpdateNameFormat -> {
                _selectedNameFormat.value = event.newNameFormat
                userPreferenceRepository.editIntPref(prefKeys.NAME_FORMAT,event.newNameFormat)
            }

            is SettingsUiEvent.ToggleShowContactNameFormatDialog -> {
                _showNameFormatDialog.value = !_showNameFormatDialog.value
            }
        }
    }

}