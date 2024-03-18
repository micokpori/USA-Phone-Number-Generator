package com.rfcreations.usaphonenumbergenerator.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.FileProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File

object ContactUtil {
    private const val fileProviderAuthority =
        "com.rfcreations.usaphonenumbergenerator.provider"
    private const val contactsFolderName = Constants.CONTACTS_FOLDER_NAME
    fun getContactFiles(context: Context): Flow<List<File>> = flow {
        val exportDir = File(context.filesDir, contactsFolderName)
        while (true) {
            val contactFiles = if (exportDir.exists() && exportDir.isDirectory) {
                exportDir.listFiles()?.toList() ?: emptyList()
            } else {
                emptyList()
            }
            emit(contactFiles)
            delay(500)
        }
    }

    fun deleteContactFile(contactFile: File) = contactFile.delete()

    fun deleteAllContacts(context: Context) {
        val contactsDir = File(context.filesDir, contactsFolderName)
        try {
            contactsDir.deleteRecursively()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    fun shareContactFile(contactFile: File, context: Context) {
        val contactFileUri = FileProvider.getUriForFile(
            context,
            fileProviderAuthority,
            contactFile.also {
                it.nameWithoutExtension.slice(0..6)
            }
        )
        val sendIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/x-vcard"
            putExtra(Intent.EXTRA_STREAM, contactFileUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    fun viewContactFile(contactFile: File, context: Context) {
        val contactFileUri = FileProvider.getUriForFile(
            context,
            fileProviderAuthority,
            contactFile
        )
        val viewIntent = Intent(Intent.ACTION_VIEW).apply {
            data = contactFileUri
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(viewIntent)
    }
}