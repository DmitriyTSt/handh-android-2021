package ru.dmitriyt.lesson9

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.util.concurrent.TimeUnit

class StartService : Service() {

    companion object {
        fun createStartIntent(context: Context): Intent {
            return Intent(context, StartService::class.java)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("TAG", "onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAG", "onStartCommand $startId")
        someTask()
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.d("TAG", "onDestroy")
        super.onDestroy()
    }

    private fun someTask() {
        Thread {
            for (i in 1..5) {
                Log.d("TAG", "i = $i")
                try {
                    TimeUnit.SECONDS.sleep(1)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            val intent = Intent(MainActivity.ACTION)
            intent.putExtra("test", "hello")
            sendBroadcast(intent)
            //LocalBroadcastManager.getInstance(StartService.this).sendBroadcast(intent);
            stopSelf()
        }.start()
    }
}