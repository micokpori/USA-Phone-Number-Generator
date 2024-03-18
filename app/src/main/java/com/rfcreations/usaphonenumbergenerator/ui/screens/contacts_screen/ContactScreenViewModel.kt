package com.rfcreations.usaphonenumbergenerator.ui.screens.contacts_screen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rfcreations.usaphonenumbergenerator.utils.ContactUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactScreenViewModel @Inject constructor(
    val context: Application
) : ViewModel() {
    val getContactFiles = ContactUtil.getContactFiles(context.applicationContext)

    private val _showDeleteAllContactDialog = MutableStateFlow(false)
    val showDeleteAllContactDialog = _showDeleteAllContactDialog.asStateFlow()

    fun uiEvent(event: ContactScreenUiEvent) {
        when (event) {
            is ContactScreenUiEvent.ToggleShowDeleteAllContactDialog -> {
                _showDeleteAllContactDialog.value = !_showDeleteAllContactDialog.value
            }
            is ContactScreenUiEvent.DeleteAllContacts -> {
                viewModelScope.launch {
                    ContactUtil.deleteAllContacts(context)
                    _showDeleteAllContactDialog.value = !_showDeleteAllContactDialog.value
                }
            }
            is ContactScreenUiEvent.ViewContactFile -> {
                ContactUtil.viewContactFile(event.contactFile, event.context)
            }
            is ContactScreenUiEvent.ShareContactFile -> {
                ContactUtil.shareContactFile(event.contactFile, event.context)
            }
            is ContactScreenUiEvent.DeleteContactFile -> {
                ContactUtil.deleteContactFile(event.contactFile)
            }
        }
    }

}