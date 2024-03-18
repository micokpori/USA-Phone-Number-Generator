package com.rfcreations.usaphonenumbergenerator.ui.screens.settings_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.rfcreations.usaphonenumbergenerator.R

@Composable
fun ContactNameFormatDialog(
    selectedNameFormat: Int,
    dismissRequest: () -> Unit,
    onNameFormatChanged: (Int) -> Unit
) {
    AlertDialog(
        onDismissRequest = dismissRequest,
        confirmButton = { },
        title = {
            Text(text = stringResource(R.string.name_format))
        },
        text = {
            Content(selectedNameFormat, modifier = Modifier.fillMaxWidth()) { newNameFormat ->
                onNameFormatChanged(newNameFormat)
            }
        }
    )
}

@Composable
private fun Content(
    selectedNameFormat: Int,
    modifier: Modifier = Modifier,
    onNameFormatChanged: (Int) -> Unit
) {
    val contactNameFormats = stringArrayResource(id = R.array.contact_name_format_options)
    Column(modifier) {
        contactNameFormats.forEachIndexed{index,format  ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onNameFormatChanged(index)
                    }
            ) {
                RadioButton(selected = selectedNameFormat == index,
                    onClick = { onNameFormatChanged(index) }
                )
                Text(text = format)
            }
        }
    }
}