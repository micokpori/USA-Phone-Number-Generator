package com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rfcreations.usaphonenumbergenerator.R

@Composable
fun GenerateFloatingActionButton(
    toggleGenerateContactDialog: () -> Unit,
) {
    FloatingActionButton(
        onClick = {
            toggleGenerateContactDialog()
        },
        modifier = Modifier.fillMaxWidth(0.4f),
    ) {
        Text(text = stringResource(id = R.string.generate))
    }
}