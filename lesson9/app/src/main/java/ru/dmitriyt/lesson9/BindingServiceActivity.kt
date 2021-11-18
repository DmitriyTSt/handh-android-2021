package ru.dmitriyt.lesson9

import android.content.ComponentName
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.dmitriyt.lesson9.databinding.ActivityBindingServiceBinding
import android.content.Intent

import android.content.ServiceConnection
import android.os.Build
import android.os.IBinder
import android.util.Log
import ru.dmitriyt.lesson9.BindingService.MyBinder


class BindingServiceActivity : AppCompatActivity() {

    companion object {
        private const val LOG_TAG = "TAG"
    }

    private val binding by viewBinding(ActivityBindingServiceBinding::bind)

    private var bound = false
    private val sConn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            Log.d(LOG_TAG, "BindingServiceActivity onServiceConnected")
            bound = true
            myBinder = binder as MyBinder
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            // не вызовется если завершить самому
            Log.d(LOG_TAG, "BindingServiceActivity onServiceDisconnected")
            bound = false
            myBinder = null
        }
    }
    private val serviceIntent by lazy { Intent(this, BindingService::class.java) }
    private var myBinder: MyBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binding_service)

        binding.btnBind.setOnClickListener {
            bindService(serviceIntent, sConn, BIND_AUTO_CREATE)
        }
        binding.btnStart.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                startForegroundService(serviceIntent)
            } else {
                startService(serviceIntent)
            }
        }
        binding.btnStop.setOnClickListener {
            stopService(serviceIntent);
        }
        binding.btnUnBind.setOnClickListener {
            if (!bound) return@setOnClickListener
            unbindService(sConn);
            bound = false;
        }
        binding.buttonTest.setOnClickListener {
            myBinder?.service?.test("text")
        }
    }

    override fun onDestroy() {
        binding.btnUnBind.callOnClick()
        super.onDestroy()
    }
}