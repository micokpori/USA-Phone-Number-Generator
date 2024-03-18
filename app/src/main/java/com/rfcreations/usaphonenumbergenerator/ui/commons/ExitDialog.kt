package com.rfcreations.usaphonenumbergenerator.ui.commons

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.rfcreations.usaphonenumbergenerator.MainActivity
import com.rfcreations.usaphonenumbergenerator.R

@Composable
fun ExitDialog(showExitDialog: MutableState<Boolean>) {
    val context = LocalContext.current
    val currentActivity = (context as? MainActivity)
    if (showExitDialog.value) {
        AlertDialog(
            onDismissRequest = { showExitDialog.value = false },
            title = { Text(text = stringResource(id = R.string.exit)) },
            text = {
                Text(
                    text = stringResource(id = R.string.exit_app_content),
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            confirmButton = {
                TextButton(onClick = { currentActivity?.finish() }) {
                    Text(text = stringResource(id = R.string.exit))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showExitDialog.value = false}) {
                    Text(text = stringResource(id = R.string.no))
                }
            }
        )
    }
}