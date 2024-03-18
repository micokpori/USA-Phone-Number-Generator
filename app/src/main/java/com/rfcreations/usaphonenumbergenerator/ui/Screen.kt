package com.rfcreations.usaphonenumbergenerator.ui

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("main_screen")
    data object SettingsScreen : Screen("setting_screen")
    data object ContactsScreen : Screen("my_contacts_screen")
    data object TermsScreen : Screen("login_screen")
    data object ContactUsScreen : Screen("contact_us_screen")
}
