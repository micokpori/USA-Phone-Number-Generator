package com.rfcreations.usaphonenumbergenerator.ui.screens.contacts_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rfcreations.usaphonenumbergenerator.R
import com.rfcreations.usaphonenumbergenerator.ui.screens.contacts_screen.ContactScreenUiEvent
import com.rfcreations.usaphonenumbergenerator.ui.screens.contacts_screen.ContactScreenViewModel
import com.rfcreations.usaphonenumbergenerator.utils.ContactUtil
import java.io.File

@Composable
fun IndividualContactFile(
    contactScreenViewModel: ContactScreenViewModel,
    contactFile: File,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .clickable { ContactUtil.viewContactFile(contactFile, context) }
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(id = R.drawable.vcf_file_icon),
            contentDescription = null,
            modifier.weight(0.1f)
        )
        Column(modifier.weight(0.8f)) {

            Text(
                text = contactFile.nameWithoutExtension.slice(0..27),
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = contactFile.nameWithoutExtension.removeRange(0..27),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight(250),
                fontStyle = FontStyle.Italic,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        IconButton(
            modifier = modifier.weight(0.1f),
            onClick = { isDropDownMenuExpanded = !isDropDownMenuExpanded }
        ) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
        }
        DropDownOptions(
            isDropDownMenuExpanded,
            viewContactFileAction = {
                isDropDownMenuExpanded = false
                contactScreenViewModel.uiEvent(
                    ContactScreenUiEvent.ViewContactFile(contactFile, context)
                )
            },
            shareContactFileAction = {
                isDropDownMenuExpanded = false
                contactScreenViewModel.uiEvent(
                    ContactScreenUiEvent.ShareContactFile(contactFile, context)
                )
            },
            deleteContactFileAction = {
                isDropDownMenuExpanded = false
                contactScreenViewModel.uiEvent(
                    ContactScreenUiEvent.DeleteContactFile(contactFile)
                )
            },
            dismissDropDownOptions = { isDropDownMenuExpanded = !isDropDownMenuExpanded }
        )
    }
    Divider(color = MaterialTheme.colorScheme.outlineVariant)
}

@Composable
private fun DropDownOptions(
    isDropDownMenuExpanded: Boolean,
    viewContactFileAction: () -> Unit,
    shareContactFileAction: () -> Unit,
    deleteContactFileAction: () -> Unit,
    dismissDropDownOptions: () -> Unit
) {
    Row(horizontalArrangement = Arrangement.End) {
        DropdownMenu(
            expanded = isDropDownMenuExpanded,
            onDismissRequest = dismissDropDownOptions
        ) {

            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.open)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inverseSurface
                    )
                },
                onClick = viewContactFileAction
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.share)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inverseSurface
                    )
                },
                onClick = shareContactFileAction
            )
            DropdownMenuItem(
                text = { Text(text = stringResource(R.string.delete)) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.inverseSurface
                    )
                },
                onClick = deleteContactFileAction
            )
        }
    }
}

