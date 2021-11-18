package ru.dmitriyt.lesson9

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.dmitriyt.lesson9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val ACTION = "ru.dmitriyt.lesson9.TEST_ACTION"
    }

    private val binding by viewBinding(ActivityMainBinding::bind)

    private var broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("TAG", "onRecieve! + ${intent?.getStringExtra("test")}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.button1.setOnClickListener {
            startService(StartService.createStartIntent(this))
        }
        binding.button2.setOnClickListener {
            stopService(StartService.createStartIntent(this))
        }

        registerReceiver(broadcastReceiver, IntentFilter(ACTION))
    }

    override fun onDestroy() {
        unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }
}