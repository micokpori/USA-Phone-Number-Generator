package com.rfcreations.usaphonenumbergenerator.ui.screens.contacts_screen

import android.content.Context
import java.io.File

sealed class ContactScreenUiEvent {
    data object ToggleShowDeleteAllContactDialog: ContactScreenUiEvent()
    data object DeleteAllContacts: ContactScreenUiEvent()
    data class ViewContactFile(val contactFile: File, val context: Context): ContactScreenUiEvent()
    data class ShareContactFile(val contactFile: File, val context: Context): ContactScreenUiEvent()
    data class DeleteContactFile(val contactFile: File): ContactScreenUiEvent()
}