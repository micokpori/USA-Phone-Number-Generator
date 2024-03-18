package com.rfcreations.usaphonenumbergenerator.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.ui.Screen

@Composable
fun BottomNavigationBar(navController: NavController) {

    NavigationBar {
        val backStackEntry = navController.currentBackStackEntryAsState()

        NavigationBarItem(
            selected = Screen.HomeScreen.route == backStackEntry.value?.destination?.route,
            onClick = {
                if (Screen.HomeScreen.route != backStackEntry.value?.destination?.route) {
                    navController.navigate(Screen.HomeScreen.route)
                }
            },
            label = {
                if (Screen.HomeScreen.route == backStackEntry.value?.destination?.route) {
                    Text(text = stringResource(R.string.home))
                }
            },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = MaterialTheme.colorScheme.primary),
            icon = {
                Icon(Icons.Default.Home, null)
            }
        )
        NavigationBarItem(
            selected = Screen.ContactsScreen.route == backStackEntry.value?.destination?.route,
            onClick = {
                if (Screen.ContactsScreen.route != backStackEntry.value?.destination?.route) {
                    navController.navigate(Screen.ContactsScreen.route)
                    }
            },
            label = {
                if (Screen.ContactsScreen.route == backStackEntry.value?.destination?.route) {
                    Text(text = stringResource(id = R.string.contacts))
                }
            },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = MaterialTheme.colorScheme.primary),
            icon = {
                Icon(Icons.Default.AccountBox, null)
            }
        )
        NavigationBarItem(
            selected = Screen.SettingsScreen.route == backStackEntry.value?.destination?.route,
            onClick = {
                if (Screen.SettingsScreen.route != backStackEntry.value?.destination?.route) {
                    navController.navigate(Screen.SettingsScreen.route)
                }
            },
            label = {
                if (Screen.SettingsScreen.route == backStackEntry.value?.destination?.route) {
                    Text(text = stringResource(R.string.settings))
                }
            },
            colors = NavigationBarItemDefaults.colors(selectedIconColor = MaterialTheme.colorScheme.primary),
            icon = {
                Icon(Icons.Default.Settings, null)
            }
        )
    }
}