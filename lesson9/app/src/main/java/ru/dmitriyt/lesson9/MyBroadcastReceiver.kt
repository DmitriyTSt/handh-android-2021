package ru.dmitriyt.lesson9

import android.widget.Toast
import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log
import java.lang.StringBuilder

class MyBroadcastReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG = "TAG"
    }

    override fun onReceive(context: Context?, intent: Intent) {
        val sb = StringBuilder()
        sb.append("Action: ${intent.action}\n")
        sb.append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
        val log = sb.toString()
        Log.d(TAG, log)
        Toast.makeText(context, log, Toast.LENGTH_LONG).show()
    }
}