package com.rfcreations.usaphonenumbergenerator.ui.screens.terms_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.ui.Screen

@Composable
fun TermsScreen(navController: NavController) {
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
    Scaffold(
        topBar = { TermsAppBar(navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(start = 8.dp, end = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                TermsHeader(
                    content = termsHead,
                    modifier = Modifier.fillMaxWidth(),
                    style = typography.titleLarge
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
}

@Composable
fun TermsHeader(
    modifier: Modifier = Modifier,
    content: String,
    textAlign: TextAlign? = TextAlign.Center,
    style: TextStyle = typography.titleMedium
) {
    Text(modifier = modifier, text = content, textAlign = textAlign, style = style)
}

@Composable
fun TermsText(
    modifier: Modifier = Modifier,
    content: String,
    textAlign: TextAlign? = null,
    style: TextStyle = typography.bodyMedium
) {
    Text(text = content,modifier = modifier, textAlign = textAlign, style = style)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = "Terms and conditions")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ), navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Screen.SettingsScreen.route)
            }) {
                Icon(Icons.Filled.ArrowBack, null)
            }
        })
}

@Preview(showSystemUi = true)
@Composable
fun TermsPreview() {
    TermsScreen(navController = rememberNavController())
}

