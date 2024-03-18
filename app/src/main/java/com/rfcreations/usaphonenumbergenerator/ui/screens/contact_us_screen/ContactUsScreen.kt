package com.rfcreations.usaphonenumbergenerator.ui.screens.contact_us_screen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.ui.Screen
import com.rfcreations.usaphonenumbergenerator.utils.urlOpener

@Composable
fun ContactUsScreen(navController: NavController) {
    Scaffold(topBar = {
        ContactUsAppBar(navController)
    }) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValue)
        ) {
            SocialMediaColumn(
                modifier = Modifier.fillMaxWidth(),
                socialMediaName = stringResource(R.string.telegram),
                R.drawable.telegram_icon,
                urlToOpen = stringResource(R.string.telegram_contact_url)
            )
            SocialMediaColumn(
                modifier = Modifier.fillMaxWidth(),
                socialMediaName = stringResource(R.string.whatsapp),
                R.drawable.whatsapp_icon,
                urlToOpen = stringResource(R.string.whatsapp_no)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ContactUsAppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.contact_us))
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

@Composable
private fun SocialMediaColumn(
    modifier: Modifier = Modifier,
    socialMediaName: String,
    @DrawableRes socialMediaIcon: Int,
    urlToOpen: String
) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .clickable {
                urlOpener(context, urlToOpen)
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painter = painterResource(id = socialMediaIcon), contentDescription = null)
        Spacer(modifier = Modifier.width(24.dp))
        Text(text = socialMediaName, style = MaterialTheme.typography.titleLarge)
    }
}