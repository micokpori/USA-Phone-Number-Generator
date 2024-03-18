package com.rfcreations.usaphonenumbergenerator.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.rfcreations.usaphonenumbergenerator.MainActivity
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.ui.screens.terms_screen.TermsHeader
import com.rfcreations.usaphonenumbergenerator.ui.screens.terms_screen.TermsText

@Composable
fun TermsDialog(dismissTermsDialog: () -> Unit) {

    val context = LocalContext.current
    val currentActivity = context as? MainActivity
    AlertDialog(
        onDismissRequest = { dismissTermsDialog() },
        title = { Text(text = stringResource(R.string.terms_and_conditions)) },
        text = {
            Terms()
        },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        ),
        confirmButton = {
            TextButton(onClick = { dismissTermsDialog() }) {
                Text(text = stringResource(R.string.accept))
            }
        },
        dismissButton = {
            TextButton(onClick = { currentActivity?.finish() }) {
                Text(text = stringResource(R.string.decline))
            }
        }
    )
}

@Composable
fun Terms() {
    val termsHead = stringResource(id = R.string.terms_and_conditions_header)
    val termsContent = stringResource(id = R.string.terms_and_conditions_content)
    val appUsageHeader = stringResource(id = R.string.app_usage_header)
    val appUsageContent = stringResource(id = R.string.app_usage_content)
    val userObligationsHeader = stringResource(id = R.string.user_obligations_header)
    val userObligationsContent = stringResource(id = R.string.user_obligations_content)
    val intellectualPropertyHeader = stringResource(id = R.string.intellectual_property_header)
    val intellectualPropertyContent = stringResource(id = R.string.intellectual_property_content)
    val terminationHeader = stringResource(id = R.string.termination_header)
    val terminationContent = stringResource(id = R.string.termination_content)
    val liabilitiesHeader = stringResource(id = R.string.liabilities_header)
    val liabilitiesContent = stringResource(id = R.string.liabilities_content)
    val termsEnd = stringResource(id = R.string.terms_end)

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),

        ) {
        item {
            TermsHeader(
                content = termsHead,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleLarge
            )
            TermsText(content = termsContent)
            TermsHeader(content = appUsageHeader, modifier = Modifier.fillMaxWidth())
            TermsText(content = appUsageContent)
            TermsHeader(content = userObligationsHeader, modifier = Modifier.fillMaxWidth())
            TermsText(content = userObligationsContent)
            TermsHeader(
                content = intellectualPropertyHeader,
                modifier = Modifier.fillMaxWidth()
            )
            TermsText(content = intellectualPropertyContent)
            TermsHeader(content = terminationHeader, modifier = Modifier.fillMaxWidth())
            TermsText(content = terminationContent)
            TermsHeader(content = liabilitiesHeader, modifier = Modifier.fillMaxWidth())
            TermsText(content = liabilitiesContent)
            TermsText(content = termsEnd)
        }
    }
}