package com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.data.ToggleState
import com.rfcreations.usaphonenumbergenerator.repository.UserPreferenceRepository
import com.rfcreations.usaphonenumbergenerator.utils.Constants
import com.rfcreations.usaphonenumbergenerator.utils.ContactGenerator
import com.rfcreations.usaphonenumbergenerator.utils.ContactUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val context: Application,
    private val userPreferenceRepository: UserPreferenceRepository
) : ViewModel() {
    private val prefKeys = Constants.PrefKeys

    private val _californiaState = MutableStateFlow(false)
    val californiaState = _californiaState.asStateFlow()

    private val _michiganState = MutableStateFlow(false)
    val michiganState = _michiganState.asStateFlow()

    private val _northCarolinaState = MutableStateFlow(false)
    val northCarolinaState = _northCarolinaState.asStateFlow()

    private val _georgiaState = MutableStateFlow(false)
    val georgiaState = _georgiaState.asStateFlow()

    private val _ohioState = MutableStateFlow(false)
    val ohioState = _ohioState.asStateFlow()

    private val _illinoisState = MutableStateFlow(false)
    val illinoisState = _illinoisState.asStateFlow()

    private val _floridaState = MutableStateFlow(false)
    val floridaState = _floridaState.asStateFlow()

    private val _texasState = MutableStateFlow(false)
    val texasState = _texasState.asStateFlow()

    private val _pennsylvaniaState = MutableStateFlow(false)
    val pennsylvaniaState = _pennsylvaniaState.asStateFlow()

    private val _newYorkState = MutableStateFlow(false)
    val newYorkState = _newYorkState.asStateFlow()

    private val _showGenerateContactDialog by lazy { MutableStateFlow(false) }
    val showGenerateContactDialog = _showGenerateContactDialog.asStateFlow()

    private val _toggleState = MutableStateFlow(ToggleState.OFF)
    val toggleState = _toggleState.asStateFlow()

    private val _isProgressBarVisible = MutableStateFlow(false)
    val isProgressBarVisible = _isProgressBarVisible.asStateFlow()

    private val selectedNameFormat: Int =
        userPreferenceRepository.getIntPref(prefKeys.NAME_FORMAT, 0)
    private lateinit var lastGeneratedContactFileUri: File

    fun uiEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.ToggleCaliforniaState -> {
                _californiaState.value = !_californiaState.value
            }
            is HomeUiEvent.ToggleMichiganState -> {
                _michiganState.value = !_michiganState.value
            }
            is HomeUiEvent.ToggleNorthCarolinaState -> {
                _northCarolinaState.value = !_northCarolinaState.value
            }
            is HomeUiEvent.ToggleGeorgiaState -> {
                _georgiaState.value = !_georgiaState.value
            }
            is HomeUiEvent.ToggleOhioState -> {
                _ohioState.value = !_ohioState.value
            }
            is HomeUiEvent.ToggleIllinoisState -> {
                _illinoisState.value = !_illinoisState.value
            }
            is HomeUiEvent.ToggleFloridaState -> {
                _floridaState.value = !_floridaState.value
            }
            is HomeUiEvent.ToggleTexasState -> {
                _texasState.value = !_texasState.value
            }
            is HomeUiEvent.TogglePennsylvaniaState -> {
                _pennsylvaniaState.value = !_pennsylvaniaState.value
            }
            is HomeUiEvent.ToggleNewYorkState -> {
                _newYorkState.value = !_newYorkState.value
            }
            is HomeUiEvent.ToggleGenerateContactDialog -> {
                val context = event.context
                if (_toggleState.value == ToggleState.OFF || _toggleState.value == ToggleState.OFF) {
                    Toast.makeText(
                        context, context.getString(R.string.select_state_toast_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    _showGenerateContactDialog.value = !_showGenerateContactDialog.value
                }
            }
            is HomeUiEvent.UpdateToggleState -> {
                _toggleState.value = event.newToggleState
            }
            is HomeUiEvent.TriStateCheckBoxClicked -> {
                when (_toggleState.value) {
                    ToggleState.INDETERMINATE -> {
                        _californiaState.value = true;_michiganState.value = true
                        _northCarolinaState.value = true;_georgiaState.value = true
                        _ohioState.value = true;_illinoisState.value = true
                        _floridaState.value = true;_texasState.value = true
                        _pennsylvaniaState.value = true;_newYorkState.value = true
                    }
                    ToggleState.ON -> {
                        _californiaState.value = false;_michiganState.value = false
                        _northCarolinaState.value = false;_georgiaState.value = false
                        _ohioState.value = false;_illinoisState.value = false
                        _floridaState.value = false;_texasState.value = false
                        _pennsylvaniaState.value = false;_newYorkState.value = false
                    }
                    ToggleState.OFF -> {
                        _californiaState.value = true;_michiganState.value = true
                        _northCarolinaState.value = true;_georgiaState.value = true
                        _ohioState.value = true;_illinoisState.value = true
                        _floridaState.value = true;_texasState.value = true
                        _pennsylvaniaState.value = true;_newYorkState.value = true
                    }
                }
            }
            is HomeUiEvent.GenerateContacts -> {
                    _isProgressBarVisible.value = true
                    val context = event.context
                    val amountToGenerate = event.amountToGenerate
                    val selectedStates = mutableListOf<String>()
                    if (californiaState.value) selectedStates.add(context.getString(R.string.california))
                    if (michiganState.value) selectedStates.add(context.getString(R.string.michigan))
                    if (georgiaState.value) selectedStates.add(context.getString(R.string.georgia))
                    if (northCarolinaState.value) selectedStates.add(context.getString(R.string.north_carolina))
                    if (illinoisState.value) selectedStates.add(context.getString(R.string.illinois))
                    if (ohioState.value) selectedStates.add(context.getString(R.string.ohio))
                    if (floridaState.value) selectedStates.add(context.getString(R.string.florida))
                    if (texasState.value) selectedStates.add(context.getString(R.string.texas))
                    if (pennsylvaniaState.value) selectedStates.add(context.getString(R.string.pennsylvania))
                    if (newYorkState.value) selectedStates.add(context.getString(R.string.new_york))

                    lastGeneratedContactFileUri = ContactGenerator(
                        context,
                        selectedStates,
                        amountToGenerate,
                        selectedNameFormat
                    ).generateAndSaveContactsAsVcf()
                    _isProgressBarVisible.value = false
                    ContactUtil.viewContactFile(lastGeneratedContactFileUri,context)

            }
        }
    }
}