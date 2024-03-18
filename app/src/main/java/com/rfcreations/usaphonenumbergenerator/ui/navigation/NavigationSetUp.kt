package com.rfcreations.usaphonenumbergenerator.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rfcreations.usaphonenumbergenerator.ui.Screen
import com.rfcreations.usaphonenumbergenerator.ui.screens.contact_us_screen.ContactUsScreen
import com.rfcreations.usaphonenumbergenerator.ui.screens.contacts_screen.ContactsScreen
import com.rfcreations.usaphonenumbergenerator.ui.screens.home_screen.HomeScreen
import com.rfcreations.usaphonenumbergenerator.ui.screens.settings_screen.Settings
import com.rfcreations.usaphonenumbergenerator.ui.screens.terms_screen.TermsScreen

@Composable
fun NavigationSetUp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val showBottomNavBar =
        when (navController.currentBackStackEntryAsState().value?.destination?.route) {
            Screen.HomeScreen.route -> true
            Screen.ContactsScreen.route -> true
            Screen.SettingsScreen.route -> true
            else -> false
        }
    Scaffold(
        bottomBar = {
            if (showBottomNavBar) BottomNavigationBar(navController)
        }
    ) {
        Box(modifier.padding(it)) {
            NavHost(navController, Screen.HomeScreen.route) {
                composable(Screen.HomeScreen.route) {
                    HomeScreen()
                }
                composable(
                    Screen.SettingsScreen.route
                ) {
                    Settings(
                        navController
                    )
                }
                composable(Screen.ContactsScreen.route) {
                    ContactsScreen(navController)
                }

                composable(Screen.TermsScreen.route) {
                    TermsScreen(navController)
                }
                composable(Screen.ContactUsScreen.route) {
                    ContactUsScreen(navController)
                }
            }
        }
    }
}