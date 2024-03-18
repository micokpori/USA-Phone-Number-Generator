package com.rfcreations.usaphonenumbergenerator.ui.screens.settings_screen

sealed class SettingsUiEvent{
    data object ToggleShowAppThemeDialog: SettingsUiEvent()
    data object ToggleShowContactNameFormatDialog: SettingsUiEvent()
    data class UpdateNameFormat(val newNameFormat: Int): SettingsUiEvent()
}
