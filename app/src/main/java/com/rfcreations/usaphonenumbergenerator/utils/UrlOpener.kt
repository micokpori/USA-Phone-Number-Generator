package com.rfcreations.usaphonenumbergenerator.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun urlOpener(context: Context, url: String) {
    val  viewIntent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }
    val viewUrl = Intent.createChooser(viewIntent, null)
    context.startActivity(viewUrl)
}