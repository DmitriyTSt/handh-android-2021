package ru.dmitriyt.lesson8.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.dmitriyt.lesson8.R
import ru.dmitriyt.lesson8.databinding.ActivityMainBinding
import ru.dmitriyt.lesson8.presentation.first.FirstFragment

class MainActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainer, FirstFragment.newInstance())
            .commit()
    }
}