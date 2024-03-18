package com.rfcreations.usaphonenumbergenerator.ui.theme

import androidx.lifecycle.ViewModel
import com.rfcreations.usaphonenumbergenerator.repository.UserPreferenceRepository
import com.rfcreations.usaphonenumbergenerator.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
@HiltViewModel
class ThemeViewModel @Inject constructor(
    val themeUiState: ThemeUiState,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel(){

    private val prefKeys = Constants.PrefKeys
    private val _firstAppRun = MutableStateFlow(
        userPreferenceRepository.getBooleanPref(prefKeys.FIRST_APP_RUN, true)
    )
    val firstAppRun = _firstAppRun.asStateFlow()
    fun toggleFirstAppRunState(){
        _firstAppRun.value = false
        userPreferenceRepository.editBooleanPref(prefKeys.FIRST_APP_RUN,false)
    }

}