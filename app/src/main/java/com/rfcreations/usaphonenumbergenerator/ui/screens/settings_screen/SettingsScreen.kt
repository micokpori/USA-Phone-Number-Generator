package com.rfcreations.usaphonenumbergenerator.ui.screens.settings_screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.ui.Screen
import com.rfcreations.usaphonenumbergenerator.ui.commons.ExitDialog
import com.rfcreations.usaphonenumbergenerator.ui.screens.settings_screen.components.AppThemeDialog
import com.rfcreations.usaphonenumbergenerator.ui.screens.settings_screen.components.ContactNameFormatDialog

@Composable
fun Settings(navController: NavController) {

    val showExitDialog = rememberSaveable { mutableStateOf(false) }
    BackHandler {
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(Screen.HomeScreen.route) { inclusive = true }
        }
    }
    ExitDialog(showExitDialog)

    val settingsViewModel = hiltViewModel<SettingsViewModel>()
    val selectedTheme = settingsViewModel.themeUiState.selectedTheme.collectAsState().value
    val showAppThemeDialog = settingsViewModel.showAppThemeDialog.collectAsState().value
    val selectedNameFormat = settingsViewModel.selectedNameFormat.collectAsState().value
    val showNameFormatDialog = settingsViewModel.showNameFormatDialog.collectAsState().value

    Scaffold(
        topBar = { SettingsAppBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            if (showAppThemeDialog) {
                AppThemeDialog(themeUiState = settingsViewModel.themeUiState) {
                    settingsViewModel.uiEvent(SettingsUiEvent.ToggleShowAppThemeDialog)
                }
            }
            if (showNameFormatDialog) {
                ContactNameFormatDialog(selectedNameFormat,
                    dismissRequest = {
                        settingsViewModel.uiEvent(
                            SettingsUiEvent.ToggleShowContactNameFormatDialog
                        )
                    }) {
                    settingsViewModel.uiEvent(
                        SettingsUiEvent.UpdateNameFormat(it)
                    )
                    settingsViewModel.uiEvent(
                        SettingsUiEvent.ToggleShowContactNameFormatDialog
                    )
                }
            }
            AppTheme(selectedTheme) {
                settingsViewModel.uiEvent(SettingsUiEvent.ToggleShowAppThemeDialog)
            }
            ExportFormat(modifier = Modifier)
            ContactNameFormat(selectedNameFormat) {
                settingsViewModel.uiEvent(
                    SettingsUiEvent.ToggleShowContactNameFormatDialog
                )
            }
            NeedHelp(modifier = Modifier)
            Terms(modifier = Modifier, navController)
            ContactUs(modifier = Modifier, navController)
            Exit {
                showExitDialog.value = true
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAppBar(
    navController: NavController
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.settings)) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.HomeScreen.route) { inclusive = true }
                }
            }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        }
    )
}

@Composable
fun AppTheme(
    themeSelectedOption: Int,
    toggleAppThemeDialog: () -> Unit
) {
    val themeOptions = stringArrayResource(id = R.array.theme_options)
    val selectedTheme = when(themeSelectedOption){
        0 -> themeOptions[0]
        1 -> themeOptions[1]
        else -> themeOptions[2]
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                toggleAppThemeDialog()
            }
            .padding(18.dp)
    ) {
        Text(
            text = stringResource(id = R.string.app_theme),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(text = selectedTheme, style = MaterialTheme.typography.bodyLarge)
    }

}

@Composable
private fun ExportFormat(modifier: Modifier) {
    var showDropDown by remember { mutableStateOf(false) }
    var exportFormat by remember { mutableStateOf("VCF") }
    Column(
        modifier
            .fillMaxWidth()
            .clickable {
                showDropDown = true
            }
            .padding(18.dp)
    ) {
        Text(
            text = stringResource(R.string.export_format),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier.height(4.dp))
        Text(text = exportFormat, style = MaterialTheme.typography.bodyLarge)
    }
    Row(modifier.padding(start = 9999.dp, end = 12.dp)) {
        DropdownMenu(
            expanded = showDropDown,
            onDismissRequest = { showDropDown = false },
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.export_as_vcf)) },
                onClick = { showDropDown = false; exportFormat = "VCF" })
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.export_as_csv)) },
                enabled = false,
                onClick = { showDropDown = false; exportFormat = "CSV" })
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.export_as_txt)) },
                enabled = false,
                onClick = { showDropDown = false; exportFormat = "TXT" })
        }
    }
}

@Composable
private fun ContactNameFormat(
    selectedNameFormat: Int,
    toggleNameFormatDialog: () -> Unit
) {
    val nameFormats = stringArrayResource(id = R.array.contact_name_format_options)
    val nameFormat = when(selectedNameFormat){
        0 -> nameFormats[0] //Use phone number as name
        1 -> nameFormats[1] //Use realistic names
        else -> nameFormats[2] //Use random names
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                toggleNameFormatDialog()
            }
            .padding(18.dp)
    ) {
        Text(
            text = stringResource(id = R.string.name_format),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = nameFormat, style = MaterialTheme.typography.bodyLarge)
    }

}

@Composable
private fun RealisticNameFormat(
    realisticContactName: Boolean,
    useNumberAsName: Boolean,
    toggleRealistName: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (!useNumberAsName) {
                    Modifier.clickable {
                        toggleRealistName()
                    }
                } else {
                    Modifier
                }
            )
            .padding(18.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(0.8f)) {
                Text(
                    text = stringResource(R.string.realistic_contact_name),
                    style = MaterialTheme.typography.titleLarge
                )
                /*                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Use realistic contact names at the expense of slower generating speed",
                                    style = MaterialTheme.typography.bodyMedium
                                )*/
            }
            Switch(
                enabled = !useNumberAsName,
                checked = realisticContactName,
                onCheckedChange = {
                    toggleRealistName()
                },
                modifier = Modifier
                    .weight(0.2f)
            )
        }
    }

}

@Composable
private fun UseNumberAsName(
    useNumberAsName: Boolean,
    realisticContactName: Boolean,
    toggleUseNumberAsName: () -> Unit,
    toggleRealisticName: (Boolean) -> Unit,

    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                toggleUseNumberAsName()
                if (realisticContactName || useNumberAsName) toggleRealisticName(false)
            }
            .padding(18.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.use_phone_number_as_name),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(0.8f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Switch(
                checked = useNumberAsName,
                onCheckedChange = {
                    toggleUseNumberAsName()
                    if (realisticContactName || useNumberAsName) toggleRealisticName(false)
                },
                modifier = Modifier
                    .weight(0.2f)
            )
        }
    }
}

@Composable
private fun NeedHelp(modifier: Modifier) {
    val context = LocalContext.current
    val comingSoonText = stringResource(id = R.string.coming_soon)
    Column(
        modifier
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, comingSoonText, Toast.LENGTH_SHORT)
                    .show()
            }
            .padding(18.dp)
    ) {
        Text(text = stringResource(R.string.need_help), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier.height(4.dp))
        Text(
            text = stringResource(R.string.watch_a_quick_video_on_how_to_use_this_app),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun Terms(modifier: Modifier, navController: NavController) {
    Column(
        modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.TermsScreen.route)
            }
            .padding(18.dp)
    ) {
        Text(
            text = stringResource(id = R.string.terms_and_conditions),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier.height(4.dp))
    }
}

@Composable
private fun ContactUs(modifier: Modifier, navController: NavController) {
    Column(
        modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(Screen.ContactUsScreen.route)
            }
            .padding(18.dp)
    ) {
        Text(
            text = stringResource(R.string.contact_us),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier.height(4.dp))
        Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Image(painterResource(id = R.drawable.telegram_icon), null)
            Image(painterResource(id = R.drawable.whatsapp_icon), null)
            Image(painterResource(id = R.drawable.twitter_icon), null)
            Image(painterResource(id = R.drawable.facebook_icon), null)
        }
        Spacer(modifier.height(6.dp))
    }
}

@Composable
private fun Exit(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(18.dp)
    ) {
        Text(text = stringResource(id = R.string.exit), style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(4.dp))
    }
}

