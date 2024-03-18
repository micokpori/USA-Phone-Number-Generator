package com.rfcreations.usaphonenumbergenerator.ui.screens.contacts_screen.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.rfcreations.usaphonenumbergenerator.R


@Composable
fun DeleteAllContactDialog(
    deleteAllContactsAction:()-> Unit,
    dismissDeleteAllContactDialog: () -> Unit
) {
    AlertDialog(
        onDismissRequest =  dismissDeleteAllContactDialog,
        confirmButton = {
            TextButton(onClick = deleteAllContactsAction) {
                Text(text = stringResource(R.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = dismissDeleteAllContactDialog) {
                Text(text = stringResource(R.string.cancel))
            }
        },
        title = {
            Text(text = stringResource(R.string.delete))
        },
        text = {
            Text(text = stringResource(R.string.delete_warning_msg))
        }
    )
}
