package com.rfcreations.usaphonenumbergenerator.ui.screens.contacts_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.ui.Screen
import com.rfcreations.usaphonenumbergenerator.ui.screens.contacts_screen.components.DeleteAllContactDialog
import com.rfcreations.usaphonenumbergenerator.ui.screens.contacts_screen.components.IndividualContactFile

@Composable
fun ContactsScreen(navController: NavController) {
    val contactScreenViewModel = hiltViewModel<ContactScreenViewModel>()
    val getContactFiles = contactScreenViewModel.getContactFiles.collectAsState(emptyList()).value
    val showDeleteAllContactDialog =
        contactScreenViewModel.showDeleteAllContactDialog.collectAsState().value
    BackHandler {
        navController.navigate(Screen.HomeScreen.route) {
            popUpTo(Screen.HomeScreen.route) { inclusive = true }
        }
    }
    if (showDeleteAllContactDialog) {
        DeleteAllContactDialog(
            deleteAllContactsAction = {
                contactScreenViewModel.uiEvent(ContactScreenUiEvent.DeleteAllContacts)
            }, dismissDeleteAllContactDialog = {
                contactScreenViewModel.uiEvent(ContactScreenUiEvent.ToggleShowDeleteAllContactDialog)
            }
        )
    }

    Scaffold(
        topBar = {
            AppBar {
                contactScreenViewModel.uiEvent(
                    ContactScreenUiEvent.ToggleShowDeleteAllContactDialog
                )
            }
        }
    ) { innerPadding ->
        if (getContactFiles.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(R.string.no_generated_contact_file),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        } else {
            LazyColumn(contentPadding = innerPadding) {
                items(
                    getContactFiles
                        .sortedBy { it.lastModified() }
                        .reversed()

                ) { contactFile ->
                    IndividualContactFile(
                        contactScreenViewModel,
                        contactFile,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(toggleShowDeleteContactDialog: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.generated_contacts)) },
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        actions = {
            IconButton(
                onClick = toggleShowDeleteContactDialog,
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = stringResource(R.string.delete_all)
                )
            }
        }
    )
}
