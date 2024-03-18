package com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen

import android.content.Context
import com.rfcreations.usaphonenumbergenerator.data.ToggleState

sealed class HomeUiEvent {
    data object ToggleCaliforniaState : HomeUiEvent()
    data object ToggleMichiganState : HomeUiEvent()
    data object ToggleNorthCarolinaState : HomeUiEvent()
    data object ToggleGeorgiaState : HomeUiEvent()
    data object ToggleOhioState : HomeUiEvent()
    data object ToggleIllinoisState : HomeUiEvent()
    data object ToggleFloridaState : HomeUiEvent()
    data object ToggleTexasState : HomeUiEvent()
    data object TogglePennsylvaniaState : HomeUiEvent()
    data object ToggleNewYorkState : HomeUiEvent()
    data object TriStateCheckBoxClicked : HomeUiEvent()
    data class UpdateToggleState(val newToggleState: ToggleState) : HomeUiEvent()
    data class ToggleGenerateContactDialog(val context: Context) : HomeUiEvent()
    data class GenerateContacts(val context: Context, val amountToGenerate: Int) : HomeUiEvent()
}
