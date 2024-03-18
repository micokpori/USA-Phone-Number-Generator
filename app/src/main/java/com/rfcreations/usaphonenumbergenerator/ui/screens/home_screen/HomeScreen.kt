package com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.ui.commons.ExitDialog
import com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.components.GenerateFloatingActionButton
import com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.components.GenerateUsaContactDialog
import com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.components.SelectAllCheckBox
import com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.components.UsaStateCard

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {

    val showExitDialog = rememberSaveable { mutableStateOf(false) }
    BackHandler {
        showExitDialog.value = !showExitDialog.value
    }
    ExitDialog(showExitDialog)
    val showGenerateContactDialog = homeViewModel.showGenerateContactDialog.collectAsState().value
    val context = LocalContext.current

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

    Scaffold(
        topBar = {},
        containerColor = colorScheme.secondaryContainer.copy(0.5f),
        floatingActionButton = {
            GenerateFloatingActionButton {
                homeViewModel.uiEvent(HomeUiEvent.ToggleGenerateContactDialog(context))
            }
        }
    ) { innerPadding ->
        if (showGenerateContactDialog)
            GenerateUsaContactDialog(homeViewModel) { toggleGenerateDialog ->
                homeViewModel.uiEvent(toggleGenerateDialog)
            }
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
        ) {
            item {
                SelectAllCheckBox(homeViewModel)
                UsaStateCard(
                    isChecked = californiaState,
                    usaStateName = stringResource(id = R.string.california),
                    modifier = Modifier.fillMaxWidth(),
                    updateCheckedState = {
                        homeViewModel.uiEvent(
                            HomeUiEvent.ToggleCaliforniaState
                        )
                    }
                )
                UsaStateCard(
                    isChecked = michiganState,
                    usaStateName = stringResource(id = R.string.michigan),
                    modifier = Modifier.fillMaxWidth(),
                    updateCheckedState = {
                        homeViewModel.uiEvent(
                            HomeUiEvent.ToggleMichiganState
                        )
                    }
                )
                UsaStateCard(
                    isChecked = northCarolinaState,
                    usaStateName = stringResource(id = R.string.north_carolina),
                    modifier = Modifier.fillMaxWidth(),
                    updateCheckedState = {
                        homeViewModel.uiEvent(
                            HomeUiEvent.ToggleNorthCarolinaState
                        )
                    }
                )
                UsaStateCard(
                    isChecked = georgiaState,
                    usaStateName = stringResource(id = R.string.georgia),
                    modifier = Modifier.fillMaxWidth(),
                    updateCheckedState = {
                        homeViewModel.uiEvent(
                            HomeUiEvent.ToggleGeorgiaState
                        )
                    }
                )
                UsaStateCard(
                    isChecked = ohioState,
                    usaStateName = stringResource(id = R.string.ohio),
                    modifier = Modifier.fillMaxWidth(),
                    updateCheckedState = {
                        homeViewModel.uiEvent(
                            HomeUiEvent.ToggleOhioState
                        )
                    }
                )
                UsaStateCard(
                    isChecked = illinoisState,
                    usaStateName = stringResource(id = R.string.illinois),
                    modifier = Modifier.fillMaxWidth(),
                    updateCheckedState = {
                        homeViewModel.uiEvent(
                            HomeUiEvent.ToggleIllinoisState
                        )
                    }
                )
                UsaStateCard(
                    isChecked = floridaState,
                    usaStateName = stringResource(id = R.string.florida),
                    modifier = Modifier.fillMaxWidth(),
                    updateCheckedState = {
                        homeViewModel.uiEvent(
                            HomeUiEvent.ToggleFloridaState
                        )
                    }
                )
                UsaStateCard(
                    isChecked = texasState,
                    usaStateName = stringResource(id = R.string.texas),
                    modifier = Modifier.fillMaxWidth(),
                    updateCheckedState = {
                        homeViewModel.uiEvent(
                            HomeUiEvent.ToggleTexasState
                        )
                    }
                )
                UsaStateCard(
                    isChecked = pennsylvaniaState,
                    usaStateName = stringResource(id = R.string.pennsylvania),
                    modifier = Modifier.fillMaxWidth(),
                    updateCheckedState = {
                        homeViewModel.uiEvent(
                            HomeUiEvent.TogglePennsylvaniaState
                        )
                    }
                )
                UsaStateCard(
                    isChecked = newYorkState,
                    usaStateName = stringResource(id = R.string.new_york),
                    modifier = Modifier.fillMaxWidth(),
                    updateCheckedState = {
                        homeViewModel.uiEvent(
                            HomeUiEvent.ToggleNewYorkState
                        )
                    }
                )


            }
        }
    }
}







