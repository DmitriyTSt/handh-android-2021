package ru.dmitriyt.lesson9

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.os.Binder
import androidx.core.app.NotificationCompat
import android.content.Intent
import android.graphics.Color
import android.os.IBinder
import android.util.Log

class BindingService : Service() {

    companion object {
        private const val LOG_TAG = "TAG"
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(LOG_TAG, "MyService onCreate")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "MyService onStartCommand")
        startForeground(10, createNotification())
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(LOG_TAG, "MyService onBind")
        return MyBinder()
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(LOG_TAG, "MyService onRebind")
    }

    override fun onUnbind(intent: Intent?): Boolean {

        //рассказать
        //Return true if you would like to have the service's onRebind(Intent) method later called when new clients bind to it.
        Log.d(LOG_TAG, "MyService onUnbind")
        return true
        //return true;
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "MyService onDestroy")
    }

    private fun createNotification(): Notification {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, "chanelId")
        builder.setWhen(System.currentTimeMillis())
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.color = Color.BLUE
        builder.setContentTitle("Foreground service")
        builder.setOngoing(true)
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setCategory(NotificationCompat.CATEGORY_SERVICE)
        return builder.build()
    }

    fun test(str: String) {
        Log.d(LOG_TAG, "hello from service: $str")
    }

    internal inner class MyBinder : Binder() {
        val service: BindingService
            get() = this@BindingService
    }
}