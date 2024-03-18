package com.rfcreations.usaphonenumbergenerator.utils

import android.util.Log
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getActivationCode(): Any {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val time = format.format(Calendar.getInstance().time)
    return try {
        lateinit var code: String
         Log.d("openConnection", "opening connection $time")
        val connection = URL("https://freezdom.netlify.app/code/").openConnection().apply {
            connectTimeout = 2500
            readTimeout = 2500
        }

        Log.d("openConnectionFinished", "finished opening connection $time")
        run {
            Log.d("getting code", "getting inputStream from run block $time")
            code = connection.getInputStream().bufferedReader().readText()
            Log.d("finished", "Finished getting code $time")
        }
        code
    } catch (e: Exception) {
        e
    }
}