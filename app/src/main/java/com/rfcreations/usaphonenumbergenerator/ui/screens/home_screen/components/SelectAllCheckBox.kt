package com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import com.rfcreations.usaphonenumbergenerator.data.ToggleState
import com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.HomeUiEvent
import com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.HomeViewModel

@Composable
fun SelectAllCheckBox(homeViewModel: HomeViewModel) {
    val toggleState = homeViewModel.toggleState.collectAsState()
    val californiaState = homeViewModel.californiaState.collectAsState().value
    val michiganState = homeViewModel.michiganState.collectAsState().value
    val northCarolinaState = homeViewModel.northCarolinaState.collectAsState().value
    val georgiaState = homeViewModel.georgiaState.collectAsState().value
    val ohioState = homeViewModel.ohioState.collectAsState().value
    val illinoisState = homeViewModel.illinoisState.collectAsState().value
    val floridaState = homeViewModel.floridaState.collectAsState().value
    val texasState = homeViewModel.texasState.collectAsState().value
    val pennsylvaniaState = homeViewModel.pennsylvaniaState.collectAsState().value
    val newYorkState = homeViewModel.newYorkState.collectAsState().value
    if (
        californiaState && michiganState &&
        northCarolinaState && georgiaState &&
        ohioState && illinoisState &&
        floridaState && texasState &&
        pennsylvaniaState && newYorkState
    ) {
        homeViewModel.uiEvent(
            HomeUiEvent.UpdateToggleState(
                ToggleState.ON
            )
        )
    } else if (
        !californiaState && !michiganState &&
        !northCarolinaState && !georgiaState &&
        !ohioState && !illinoisState &&
        !floridaState && !texasState &&
        !pennsylvaniaState && !newYorkState
    ) {
        homeViewModel.uiEvent(
            HomeUiEvent.UpdateToggleState(
                ToggleState.OFF
            )
        )
    } else {
        homeViewModel.uiEvent(
            HomeUiEvent.UpdateToggleState(
                ToggleState.INDETERMINATE
            )
        )
    }
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

        TriStateCheckbox(
            state = when (toggleState.value) {
                ToggleState.ON -> ToggleableState.On
                ToggleState.OFF -> ToggleableState.Off
                else -> ToggleableState.Indeterminate
            },
            onClick = {
                homeViewModel.uiEvent(HomeUiEvent.TriStateCheckBoxClicked)
            }
        )
    }
}