package ru.handh.lesson5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    private val launcher = registerForActivityResult(SecondActivityContract()) { username ->
        username
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialButton>(R.id.button2).setOnClickListener {
            launcher.launch(User("name", "surname"))
        }
    }
}